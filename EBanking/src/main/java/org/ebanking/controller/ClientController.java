package org.ebanking.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.ebanking.dao.CompteRepository;
import org.ebanking.dao.VirementRepository;
import org.ebanking.entity.Compte;
import org.ebanking.entity.Virement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping(value = "/Client")
//@Secured(value = {"ROLE_CLIENT"})
public class ClientController {
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private VirementRepository virementRepository;

	@RequestMapping(value = "/virement", method = RequestMethod.GET)
	public Object[] virement(@RequestParam(value = "ribSource") Long ribSource,
			@RequestParam(name = "ribDestination") Long ribDestination,
			@RequestParam(name = "montant") Double montant) throws ParseException {
		
		Compte compteSource = compteRepository.findByRib(ribSource);
		Compte compteDestination = compteRepository.findByRib(ribDestination);
		
		System.out.println(ribSource + ", " + ribDestination + ", " + montant);
		
		if(compteSource == null || compteDestination == null) {
			//l'un des rib est eroné
			return new Object[] {-1, null};
		}else {
			if(compteSource.getSold() >= montant) {
				compteSource.setSold(compteSource.getSold() - montant);
				compteDestination.setSold(compteDestination.getSold() + montant);
				
				compteRepository.save(compteSource);
				compteRepository.save(compteDestination);
				
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Virement v = new Virement(compteSource, compteDestination, montant, df.parse(df.format(Calendar.getInstance().getTime())));
				virementRepository.save(v);
				
				//transaction effectuée avec success 
				return new Object[] {1, compteSource};
			}
			//solde insuffisant
			return new Object[] {-2, null};
		}
	}
	
	@RequestMapping(value = "/mes-comptes")
	public List<Compte> mesComptes(int idClient){
		return compteRepository.findByClientId(idClient);
	}

}
