package org.ebanking.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.ebanking.dao.CompteRepository;
import org.ebanking.dao.DonRepository;
import org.ebanking.dao.ReclamationRepository;
import org.ebanking.dao.VirementRepository;
import org.ebanking.entity.Compte;
import org.ebanking.entity.Don;
import org.ebanking.entity.PaiementService;
import org.ebanking.entity.Reclamation;
import org.ebanking.entity.Virement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping(value = "/Client")
//@Secured(value = {"ROLE_CLIENT"})
public class ClientController {
	
	public static final String datePattern = "dd/MM/yyyy HH:mm:ss";
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private VirementRepository virementRepository;
	
	@Autowired
	private ReclamationRepository reclamationRepository; 
	
	@Autowired
	private DonRepository donRepository;

	/**
	 * -------------Virements---------------
	 */
	
	/**
	 * 
	 * @param ribSource
	 * @param ribDestination
	 * @param montant
	 * @return
	 * @throws ParseException
	 */
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
				
				DateFormat df = new SimpleDateFormat(datePattern);
				Virement v = new Virement(compteSource, compteDestination, 
						montant, df.parse(df.format(Calendar.getInstance().getTime())));
				virementRepository.save(v);
				
				//transaction effectuée avec success 
				return new Object[] {1, compteSource};
			}
			//solde insuffisant
			return new Object[] {-2, null};
		}
	}
	
	/**
	 * 
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/{idClient}/mes-virements")
	public List<Virement> mesVirements(@PathVariable int idClient){
		List<Compte> comptes = compteRepository.findByClientId(idClient);
		List<Virement> virements = new ArrayList<>();
		
		comptes.forEach(c -> {
			virements.addAll(c.getVirementsEnvoyes());
			virements.addAll(c.getVirementsRecus());
		});
		
		return virements;
	}
	
	/**
	 * ---------------------Comptes----------------------
	 * 
	 */
	
	/**
	 * 
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/{idClient}/mes-comptes")
	public List<Compte> mesComptes(@PathVariable int idClient){
		return compteRepository.findByClientId(idClient);
	}
	
	/**
	 * 
	 * @param idClient
	 * @param idCompte
	 * @return
	 */
	@RequestMapping(value = "/{idClient}/compte/{rib}")
	public Compte unCompte(@PathVariable int idClient, @PathVariable int rib) {
		return compteRepository.findByRib((long) rib);
	}
	
	/**
	 * -------------------Reclamations-------------------
	 */
	/**
	 * 
	 * @param reclamation
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/déposer-une-réclamation", method = RequestMethod.POST)
	public Object[] deposerReclamation(@RequestBody Reclamation reclamation) throws ParseException {
		DateFormat df = new SimpleDateFormat(datePattern);
		// setting the client & the agent
		// to do
		reclamation.setDateDepot(df.parse(df.format(Calendar.getInstance().getTime())));
		reclamationRepository.save(reclamation);
		
		return new Object[] {1, reclamation};
	}
	
	/**
	 * 
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/{idClient}/mes-réclamations")
	public List<Reclamation> mesReclamations(@PathVariable int idClient){
		return reclamationRepository.findByClientId(idClient);
	}
	
	/**
	 * -----------------------Dons--------------------------
	 */
	
	@RequestMapping(value = "/faire-un-don", method = RequestMethod.POST)
	public Object[] fairUnDon(@RequestBody Don don) {
		Compte compte = compteRepository.findByRib(don.getCompte().getRib());
		
		if(compte.getSold() >= don.getMontant()) {
			compte.setSold(compte.getSold() - don.getMontant());
			compteRepository.save(compte);
			
			donRepository.save(don);
			
			return new Object[] {1, don};
		}
		return new Object[] {-1, null};
		
	}
	
	/**
	 * 
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/{idClient}/mes-dons")
	public List<Don> mesDons(@PathVariable int idClient){
		List<Compte> comptes = compteRepository.findByClientId(idClient);
		List<Don> dons = new ArrayList<>();
		
		comptes.forEach(c -> {
			dons.addAll(c.getDons());
		});
		
		return dons;
	}
	
	/**
	 * --------------------------Services payés------------------------
	 */
	
	/**
	 * 
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/{idClient}/mes-services-payés")
	public List<PaiementService> mesServicesPayes(@PathVariable int idClient){
		List<Compte> comptes = compteRepository.findByClientId(idClient);
		List<PaiementService> paiementServices = new ArrayList<>();
		
		comptes.forEach(c -> {
			paiementServices.addAll(c.getPaiementServices());
		});
		
		return paiementServices;
	}
}
