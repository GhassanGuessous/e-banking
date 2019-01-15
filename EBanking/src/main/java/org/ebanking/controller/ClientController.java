package org.ebanking.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.ebanking.dao.ClientRepository;
import org.ebanking.dao.CompteRepository;
import org.ebanking.dao.DonRepository;
import org.ebanking.dao.OrganismeRepository;
import org.ebanking.dao.PaiementServiceRepository;
import org.ebanking.dao.ReclamationRepository;
import org.ebanking.dao.RoleRepository;
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
import org.ebanking.web.inputs.ClientInput;
import org.ebanking.web.inputs.DonInput;
import org.ebanking.web.inputs.PaiementServiceInput;
import org.ebanking.web.inputs.PasswordInput;
import org.ebanking.web.inputs.ReclamationInput;
import org.ebanking.web.inputs.VirementInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/Client")
// @Secured(value = {"ROLE_CLIENT"})
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
	private RoleRepository roleRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * ---------------Creer un profil/compte----------------
	 */

	/**
	 * 
	 * @param clientInput
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Object[] signUp(@RequestBody @Valid ClientInput clientInput) throws ParseException {
		if (clientInput.getPassword().equals(clientInput.getConfirmedPassword())) {
			Client client = substitute(clientInput);
			try {
				client = clientRepository.save(client);

				Compte compte = createCompte(client);
				compteRepository.save(compte); 

				return new Object[] { "true", client, compte };
			}catch(Exception e) {
				return new Object[] {"false", e.getCause().getCause().getLocalizedMessage()};
			}
			
		}
		// mot de passe non confirmé
		return new Object[] { "false", "Mot de passe non confirmé" };
	}

	/**
	 * 
	 * @param clientInput
	 * @return
	 */
	public Client substitute(ClientInput clientInput) {
		Client client = new Client();

		client.setCin(clientInput.getCin());
		client.setPrenom(clientInput.getPrenom());
		client.setNom(clientInput.getNom());
		client.setAdresse(clientInput.getAdresse());
		client.setEmail(clientInput.getEmail());
		client.setCodePostal(clientInput.getCodePostal());
		client.setTelephone(clientInput.getTelephone());
		client.setUsername(clientInput.getUsername());
		client.setPassword(encoder.encode(clientInput.getPassword()));
		client.setRole(roleRepository.findRoleByRole("ROLE_CLIENT"));
		client.setActivated(false);

		return client;
	}

	/**
	 * 
	 * @param client
	 * @return
	 * @throws ParseException
	 */
	public Compte createCompte(Client client) throws ParseException {
		DateFormat df = new SimpleDateFormat(datePattern);
		Compte compte = new Compte();

		// rib : 16 chiffres
		compte.setRib(generateRib());
		compte.setSold(new Double(0.0));
		compte.setDateCreation(df.parse(df.format(Calendar.getInstance().getTime())));
		compte.setClient(client);

		return compte;
	}

	/**
	 * Generer un rib qui n'existe pas au préalable a la BD
	 * 
	 * @return Long
	 */
	public Long generateRib() {
		Long rib;
		Compte isExist;

		do {
			rib = (long) (Math.random() * 10000000000000000L);
			isExist = compteRepository.findByRib(rib);
		} while (isExist != null);

		return rib;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mon-profil")
	public Client monProfil(HttpServletRequest request) {
		String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
		Client client = clientRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));
		return client;
	}
	
	/**
	 * 
	 * @param request
	 * @param passwordInput
	 * @return
	 */
	@RequestMapping(value = "/modifier-mot-de-passe")
	public Object[] modifierPassword(HttpServletRequest request, @Valid @RequestBody PasswordInput passwordInput) {
		String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
		Client client = clientRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));

		if(encoder.matches(passwordInput.getOldPassword(), client.getPassword())) {
			if(passwordInput.getNewPassword().equals(passwordInput.getConfirmedPassword())) {
				client.setPassword(encoder.encode(passwordInput.getNewPassword()));
				clientRepository.save(client);
				return new Object[] {"success", client};
			}
			//new pass != confirmed pass
			return new Object[] {"Mot de passe non confirmé"};
		}
		//old pass != client pass
		return new Object[] {"l'ancien mot de passe est éroné"};
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

		if (compteSource == null || compteDestination == null) {
			// l'un des rib est eroné
			return new Object[] { "l'un des rib est éroné", null };
		} else {
			if (compteSource.getSold() >= virement.getMontant()) {
				compteSource.setSold(compteSource.getSold() - virement.getMontant());
				compteDestination.setSold(compteDestination.getSold() + virement.getMontant());

				compteRepository.save(compteSource);
				compteRepository.save(compteDestination);

				DateFormat df = new SimpleDateFormat(datePattern);
				Virement v = new Virement(compteSource, compteDestination, virement.getMontant(),
						df.parse(df.format(Calendar.getInstance().getTime())));
				virementRepository.save(v);

				// transaction effectuée avec success
				return new Object[] { "success", v };
			}
			// solde insuffisant
			return new Object[] { "solde insuffisant", null };
		}
	}

	/**
	 * 
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/mes-virements")
	public Object[] mesVirements(HttpServletRequest request) {
		String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
		Client client = clientRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));

		List<Compte> comptes = compteRepository.findByClientId(client.getId());
		List<Virement> virementsEnvoyes = new ArrayList<>();
		List<Virement> virementsRecus = new ArrayList<>();

		comptes.forEach(c -> {
			virementsEnvoyes.addAll(c.getVirementsEnvoyes());
			virementsRecus.addAll(c.getVirementsRecus());
		});

		return new Object[] {virementsEnvoyes, virementsRecus};
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
	public List<Compte> mesComptes(HttpServletRequest request) {
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
	public Object[] deposerReclamation(HttpServletRequest request,
			@RequestBody @Valid ReclamationInput reclamationInput) throws ParseException {
		DateFormat df = new SimpleDateFormat(datePattern);

		String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
		Client client = clientRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));

		if (client != null) {
			Reclamation reclamation = new Reclamation();
			reclamation.setCorps(reclamationInput.getCorps());
			reclamation.setClient(client);
			reclamation.setAgent(client.getAgent());
			reclamation.setEtat("En cours de traitement");
			reclamation.setDateDepot(df.parse(df.format(Calendar.getInstance().getTime())));

			reclamationRepository.save(reclamation);

			return new Object[] { "success", reclamation };
		}

		return new Object[] { "Client inexistant", null };
	}

	/**
	 * 
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/mes-réclamations")
	public List<Reclamation> mesReclamations(HttpServletRequest request) {
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

		if (compte != null) {
			if (compte.getSold() >= donInput.getMontant()) {
				Don don = new Don(donInput.getMontant(), organisme, compte);
				compte.setSold(compte.getSold() - donInput.getMontant());

				donRepository.save(don);
				compteRepository.save(compte);

				return new Object[] { "success", don };
			}
			// solde insuffisant
			return new Object[] { "solde insuffisant", null };
		}
		// compte introuvable
		return new Object[] { "compte introuvable", null };

	}

	/**
	 * 
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/mes-dons")
	public List<Don> mesDons(HttpServletRequest request) {
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
		SousCategorieService sousCategorieService = categorieServiceRepository
				.findById(paiementServiceInput.getSousCategorie());

		Long numeroContrat = (paiementServiceInput.getNumeroContrat() != null) ? paiementServiceInput.getNumeroContrat() : null;
		Long numeroTelephone = (paiementServiceInput.getNumeroTelephone() != null) ? paiementServiceInput.getNumeroTelephone() : null;

		if (compte != null) {
			if (compte.getSold() >= paiementServiceInput.getMontant()) {
				PaiementService paiementService = new PaiementService(numeroContrat, numeroTelephone,
						paiementServiceInput.getMontant(), sousCategorieService, compte, organisme);
				compte.setSold(compte.getSold() - paiementServiceInput.getMontant());

				paiementServiceRepository.save(paiementService);
				compteRepository.save(compte);

				return new Object[] { "success", paiementService };
			}
			// solde insuffisant
			return new Object[] { "solde insuffisant", null };
		}
		// compte introuvable
		return new Object[] { "compte introuvable", null };
	}

	/**
	 * 
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/mes-services-payés")
	public List<PaiementService> mesServicesPayes(HttpServletRequest request) {
		String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
		Client client = clientRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));

		List<Compte> comptes = compteRepository.findByClientId(client.getId());
		List<PaiementService> paiementServices = new ArrayList<>();

		comptes.forEach(c -> {
			paiementServices.addAll(c.getPaiementServices());
		});

		return paiementServices;
	}
	
	/**
	 * ------------------Statistiques-------------------
	 */
	
	@RequestMapping(value = "/mes-statistiques")
	public HashMap<String, Integer> statistiques(HttpServletRequest request) {
		String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
		Client client = clientRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));
	
		HashMap<String, Integer> stat = new HashMap<>();
		
		stat.put("nbrComptes", client.getComptes().size());
		stat.put("soldeSum", compteRepository.soldeSum(client.getId()));
		stat.put("nbrVirementsEnvoyes", nbrVirement(client, 1));
		stat.put("nbrVirementsRecus", nbrVirement(client, 2));
		
		return stat;
	}
	
	public int nbrVirement(Client client, int typeVirement) {
		int nbr = 0;
		//envoyes
		if(typeVirement == 1) {
			for (int i = 0; i < client.getComptes().size(); i++) {
				nbr += client.getComptes().get(i).getVirementsEnvoyes().size();
			}
		}
		//recus
		if(typeVirement == 2) {
			for (int i = 0; i < client.getComptes().size(); i++) {
				nbr += client.getComptes().get(i).getVirementsRecus().size();
			}
		}
		return nbr;
	}
	
	
	
	
	
	
}
