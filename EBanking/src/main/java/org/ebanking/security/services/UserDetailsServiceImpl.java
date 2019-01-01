package org.ebanking.security.services;

import java.util.ArrayList;
import java.util.Collection;

import org.ebanking.dao.ClientRepository;
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
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Client client = clientRepository.findByUsername(username);
		if(client == null) throw new UsernameNotFoundException("Bad Credentials " + username);
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(client.getRole().getRole()));
		System.out.println("------> Username: " + client.getUsername() + ", pass: " + client.getPassword() + ", Auth: " + authorities);
		return new User(client.getUsername(), client.getPassword(), authorities);
	}

}
