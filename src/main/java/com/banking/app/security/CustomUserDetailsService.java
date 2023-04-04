package com.banking.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.banking.app.entities.Customer;
import com.banking.app.exceptions.ResourceNotFoundException;
import com.banking.app.repositories.CustomerRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Customer customer = this.customerRepo.findBymobileno(username).orElseThrow(()-> new ResourceNotFoundException("User ", " mobile : "+username, 0));
		Customer customer = this.customerRepo.findBymobileno(username).orElseThrow(()-> new ResourceNotFoundException("User", "mobileNo :"+username,0));
		return customer;
	}

}
