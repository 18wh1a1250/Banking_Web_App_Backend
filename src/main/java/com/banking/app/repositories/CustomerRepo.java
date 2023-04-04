package com.banking.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.banking.app.entities.Customer;
import com.banking.app.payloads.CustomerDto;


public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	Optional<Customer> findBymobileno(String mobileno);

	
		
	
}
