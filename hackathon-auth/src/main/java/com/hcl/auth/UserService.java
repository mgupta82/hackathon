package com.hcl.auth;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userRepository.findByUsername(username)
				.map(user -> new User(user.getUsername(), 
						user.getPassword(), 
						user.isActive(), 
						user.isActive(), 
						user.isActive(), user.isActive(), 
						AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_USER")))
				.orElseThrow(()->new UsernameNotFoundException("User not found"));
	}

}
