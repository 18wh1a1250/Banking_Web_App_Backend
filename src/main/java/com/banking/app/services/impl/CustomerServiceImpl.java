package com.banking.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.banking.app.config.AppConstants;

import com.banking.app.entities.Customer;
import com.banking.app.entities.Role;
import com.banking.app.exceptions.ResourceNotFoundException;
import com.banking.app.payloads.CustomerDto;
import com.banking.app.repositories.CustomerRepo;
import com.banking.app.repositories.RoleRepo;
import com.banking.app.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		Customer customer = this.dtoToCustomer(customerDto);
		Customer savedCustomer = this.customerRepo.save(customer);
		
		return this.customerToDto(savedCustomer);
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto, Integer customerId) {
		Customer customer = this.customerRepo.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer","Customer Id",customerId));
		customer.setName(customerDto.getName());
		customer.setAcctId(customerDto.getAcctID());
		customer.setAccountNo(customerDto.getAccountNo());
		customer.setIfscCode(customerDto.getIfscCode());
		customer.setEmail(customerDto.getEmail());
		customer.setMobileno(customer.getMobileno());
		customer.setPassword(customerDto.getPassword());
		customer.setCity(customerDto.getCity());
		customer.setState(customerDto.getState());
		customer.setCountry(customerDto.getCountry());

		
		Customer updatedCustomer = this.customerRepo.save(customer);
		CustomerDto customerDto1 = this.customerToDto(updatedCustomer);
		return customerDto1;
		
		
	}

	@Override
	public CustomerDto getCustomerById(Integer customerId) {
		// TODO Auto-generated method stub
		Customer customer = this.customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "Id", customerId));
		return this.customerToDto(customer);
		
		
	}

	@Override
	public List<CustomerDto> getAllCustomers() {
		List<Customer> customers = this.customerRepo.findAll();
		List<CustomerDto> customerDtos = customers.stream().map(customer -> this.customerToDto(customer)).collect(Collectors.toList());
		return customerDtos;
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		Customer customer = this.customerRepo.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer", "Id", customerId));
		
		this.customerRepo.delete(customer);
		
	}
	
	public Customer dtoToCustomer(CustomerDto customerDto) {
		
		Customer customer = this.modelMapper.map(customerDto, Customer.class);
		return customer;
		
		
	}
	
	public CustomerDto customerToDto(Customer customer) {
		CustomerDto customerDto = this.modelMapper.map(customer, CustomerDto.class);
		return customerDto;
		
		
	}



	@Override
	public CustomerDto registerNewCustomer(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		Customer customer = this.modelMapper.map(customerDto, Customer.class);
		
		//password
		customer.setPassword(this.passwordEncoder.encode(customer.getPassword()));
		
		//roles
		
		Role role  = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		
			customer.getRoles().add(role);
			
			Customer newCustomer = this.customerRepo.save(customer);
		 
		 return this.modelMapper.map(newCustomer, CustomerDto.class);
	}
	


}
