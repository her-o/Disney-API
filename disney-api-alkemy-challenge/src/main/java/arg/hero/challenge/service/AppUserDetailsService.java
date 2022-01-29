package arg.hero.challenge.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import arg.hero.challenge.model.auth.AppUser;
import arg.hero.challenge.repository.AppUserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AppUserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 AppUser appUser = repository.findByUsername(username).orElseThrow(() -> 
		 									new UsernameNotFoundException(String.format("No user with username '%s' was found.", username)));
		 
		 User user = new User(appUser.getUsername(), 
				 			  appUser.getPassword(),
				 	 	      true, true, true, true,
				 			  getAuthorities("ROLE_USER"));
		 return user;
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		return Collections.singletonList(new SimpleGrantedAuthority(role));
	}

}
