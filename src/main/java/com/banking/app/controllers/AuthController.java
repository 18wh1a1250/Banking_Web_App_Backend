package com.banking.app.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.entities.Customer;
import com.banking.app.exceptions.ApiException;
import com.banking.app.payloads.CustomerDto;
import com.banking.app.payloads.JwtAuthRequest;
import com.banking.app.payloads.JwtAuthResponse;
import com.banking.app.security.JwtTokenHelper;
import com.banking.app.services.CustomerService;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled=true)

@RequestMapping("/api/v1/auth/")
public class AuthController{
	

	@Autowired
	private JwtTokenHelper jwtTokenHelper ;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		
		this.authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		
		JwtAuthResponse response = new JwtAuthResponse();
		
		response.setToken(token);
		response.setUser(this.modelMapper.map((Customer) userDetails, CustomerDto.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
		
	}
	
	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		}catch(BadCredentialsException e) {
			System.out.println("Inavlid details!");
			throw new ApiException("Invalid username or password");
		}
		
	}
	
	
	//register new user api
	
	@PostMapping("/register")
	public ResponseEntity<CustomerDto> registerUser(@RequestBody CustomerDto customerDto){
		
		CustomerDto registeredCustomer = this.customerService.registerNewCustomer(customerDto);	
		
		return new ResponseEntity<CustomerDto>(registeredCustomer, HttpStatus.CREATED);
	}
}