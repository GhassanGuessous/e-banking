package org.ebanking.security.services;

import java.util.ArrayList;
import java.util.Collection;

import org.ebanking.dao.AdminRepository;
import org.ebanking.dao.ClientRepository;
import org.ebanking.entity.Admin;
import org.ebanking.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Collection<GrantedAuthority> authorities = new ArrayList<>();
//		
//		Client client = clientRepository.findByUsername(username);
//		Admin admin = adminRepository.findByUsername(username);
//		
//		if(client == null && admin == null)
//			throw new UsernameNotFoundException("Bad Credentials " + username);
//		
//		if(admin != null) {
//			authorities.add(new SimpleGrantedAuthority(admin.getRole().getRole()));
//			System.out.println("------> Username: " + admin.getUsername() + ", pass: " + admin.getPassword() + ", Auth: " + authorities);
//			return new User(admin.getUsername(), admin.getPassword(), authorities);
//		}else {
//			authorities.add(new SimpleGrantedAuthority(client.getRole().getRole()));
//			System.out.println("------> Username: " + client.getUsername() + ", pass: " + client.getPassword() + ", Auth: " + authorities);
//			return new User(client.getUsername(), client.getPassword(), authorities);
//		}
//	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		
		Client client = clientRepository.findByUsername(username);
		Admin admin = adminRepository.findByUsername(username);
		
		if(client == null && admin == null)
			throw new UsernameNotFoundException("Bad Credentials " + username);
		
		if(admin != null) {
			authorities.add(new SimpleGrantedAuthority(admin.getRole().getRole()));
			System.out.println("------>Admin Username: " + admin.getUsername() + ", pass: " + admin.getPassword() + ", Auth: " + authorities);
			return new User(admin.getUsername(), admin.getPassword(), authorities);
		}else {
			authorities.add(new SimpleGrantedAuthority(client.getRole().getRole()));
			System.out.println("------> Client Username: " + client.getUsername() + ", pass: " + client.getPassword() + ", Auth: " + authorities);
			return new User(client.getUsername(), client.getPassword(), authorities);
		}
	}

	
}
