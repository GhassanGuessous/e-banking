package org.ebanking.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.ebanking.dao.ClientRepository;
import org.ebanking.dao.CompteRepository;
import org.ebanking.dao.DonRepository;
import org.ebanking.dao.OrganismeRepository;
import org.ebanking.dao.PaiementServiceRepository;
import org.ebanking.dao.ReclamationRepository;
import org.ebanking.dao.SousCategorieServiceRepository;
import org.ebanking.dao.VirementRepository;
import org.ebanking.entity.Agent;
import org.ebanking.entity.Client;
import org.ebanking.entity.Compte;
import org.ebanking.entity.Don;
import org.ebanking.entity.Organisme;
import org.ebanking.entity.PaiementService;
import org.ebanking.entity.Reclamation;
import org.ebanking.entity.SousCategorieService;
import org.ebanking.entity.Virement;
import org.ebanking.security.JwtTokenUtil;
import org.ebanking.security.SecurityConstants;
import org.ebanking.web.inputs.DonInput;
import org.ebanking.web.inputs.PaiementServiceInput;
import org.ebanking.web.inputs.ReclamationInput;
import org.ebanking.web.inputs.VirementInput;
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
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private OrganismeRepository organismeRepository;
	
	@Autowired
	private PaiementServiceRepository paiementServiceRepository;
	
	@Autowired
	private SousCategorieServiceRepository categorieServiceRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil; 
	
	@RequestMapping(value = "/all")
	public List<Client> getAllClients(){
		return clientRepository.findAll();
	}

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
	@RequestMapping(value = "/éffectuer-un-virement", method = RequestMethod.POST)
	public Object[] virement(@RequestBody @Valid VirementInput virement) throws ParseException {
		
		Compte compteSource = compteRepository.findByRib(virement.getRibSource());
		Compte compteDestination = compteRepository.findByRib(virement.getRibDestination());
		
		if(compteSource == null || compteDestination == null) {
			//l'un des rib est eroné
			return new Object[] {-1, null};
		}else {
			if(compteSource.getSold() >= virement.getMontant()) {
				compteSource.setSold(compteSource.getSold() - virement.getMontant());
				compteDestination.setSold(compteDestination.getSold() + virement.getMontant());
				
				compteRepository.save(compteSource);
				compteRepository.save(compteDestination);
				
				DateFormat df = new SimpleDateFormat(datePattern);
				Virement v = new Virement(compteSource, compteDestination, 
						virement.getMontant(), df.parse(df.format(Calendar.getInstance().getTime())));
				virementRepository.save(v);
				
				//transaction effectuée avec success 
				return new Object[] {1, v};
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
	@RequestMapping(value = "/mes-virements")
	public List<Virement> mesVirements(HttpServletRequest request){
		String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
		Client client = clientRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));
		
		List<Compte> comptes = compteRepository.findByClientId(client.getId());
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
	@RequestMapping(value = "/mes-comptes")
	public List<Compte> mesComptes(HttpServletRequest request){
		String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
		Client client = clientRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));
		return compteRepository.findByClientId(client.getId());
	}
	
	/**
	 * 
	 * @param idClient
	 * @param idCompte
	 * @return
	 */
	@RequestMapping(value = "/compte/{rib}")
	public Compte unCompte(@PathVariable int rib) {
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
	public Object[] deposerReclamation(HttpServletRequest request, @RequestBody @Valid ReclamationInput reclamationInput) throws ParseException {
		DateFormat df = new SimpleDateFormat(datePattern);
		
		String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
		Client client = clientRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));
		
		if(client != null) {
			Reclamation reclamation = new Reclamation();
			reclamation.setCorps(reclamationInput.getCorps());
			reclamation.setClient(client);
			reclamation.setAgent(client.getAgent());
			reclamation.setEtat("En cours de traitement");
			reclamation.setDateDepot(df.parse(df.format(Calendar.getInstance().getTime())));
			
			reclamationRepository.save(reclamation);
			
			return new Object[] {1, reclamation};
		}
		
		return new Object[] {-1, null};
	}
	
	/**
	 * 
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/mes-réclamations")
	public List<Reclamation> mesReclamations(HttpServletRequest request){
		String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
		Client client = clientRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));
		return reclamationRepository.findByClientId(client.getId());
	}
	
	/**
	 * -----------------------Dons--------------------------
	 */
	
	@RequestMapping(value = "/faire-un-don", method = RequestMethod.POST)
	public Object[] fairUnDon(@RequestBody @Valid DonInput donInput) {
		Compte compte = compteRepository.findByRib(donInput.getCompte());
		Organisme organisme = organismeRepository.findById(donInput.getOrganisme());
		
		if(compte != null) {
			if(compte.getSold() >= donInput.getMontant()) {
				Don don = new Don(donInput.getMontant(), organisme, compte);
				compte.setSold(compte.getSold() - donInput.getMontant());
				
				donRepository.save(don);
				compteRepository.save(compte);
				
				return new Object[] {1, don};
			}
			//solde insuffisant
			return new Object[] {-2, null};
		}
		//compte introuvable
		return new Object[] {-1, null};
		
	}
	
	/**
	 * 
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/mes-dons")
	public List<Don> mesDons(HttpServletRequest request){
		String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
		Client client = clientRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));
		
		List<Compte> comptes = compteRepository.findByClientId(client.getId());
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
	 * @param paiementService
	 * @return
	 */
	@RequestMapping("/payer-un-service")
	public Object[] payerService(@RequestBody PaiementServiceInput paiementServiceInput) {
		Compte compte = compteRepository.findByRib(paiementServiceInput.getCompte());
		Organisme organisme = organismeRepository.findById(paiementServiceInput.getOrganisme());
		SousCategorieService sousCategorieService = categorieServiceRepository.findById(paiementServiceInput.getSousCategorie());
		
		Long numeroContrat = ( paiementServiceInput.getNumeroContart() != null ) ? paiementServiceInput.getNumeroContart() : null;
		Long numeroTelephone = ( paiementServiceInput.getNumeroTelephone() != null ) ? paiementServiceInput.getNumeroTelephone() : null;
		
		if(compte != null) {
			if(compte.getSold() >= paiementServiceInput.getMontant()) {
				PaiementService paiementService = new PaiementService(
						numeroContrat, 
						numeroTelephone,
						paiementServiceInput.getMontant(),
						sousCategorieService,
						compte, 
						organisme
					);
				compte.setSold(compte.getSold() - paiementServiceInput.getMontant());
				
				paiementServiceRepository.save(paiementService);
				compteRepository.save(compte);
				
				return new Object[] {1, paiementService};
			}
			//solde insuffisant
			return new Object[] {-2, null};
		}
		//compte introuvable
		return new Object[] {-1, null};
	}
	
	/**
	 * 
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/mes-services-payés")
	public List<PaiementService> mesServicesPayes(HttpServletRequest request){
		String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
		Client client = clientRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));
		
		List<Compte> comptes = compteRepository.findByClientId(client.getId());
		List<PaiementService> paiementServices = new ArrayList<>();
		
		comptes.forEach(c -> {
			paiementServices.addAll(c.getPaiementServices());
		});
		
		return paiementServices;
	}
}
