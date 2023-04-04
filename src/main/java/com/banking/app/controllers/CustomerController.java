package com.banking.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.entities.Customer;
import com.banking.app.payloads.ApiResponse;
import com.banking.app.payloads.CustomerDto;
import com.banking.app.services.CustomerService;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AccountController accountController;


	@PostMapping("/")
	public ResponseEntity<CustomerDto> createPatient(@Valid @RequestBody CustomerDto customerDto){
		CustomerDto createCustomerDto = this.customerService.createCustomer(customerDto);
		
		return new ResponseEntity<>(createCustomerDto, HttpStatus.CREATED);
		
	}
	
//	@PreAuthorize("hasRole('ADMIN_USER')")
	@PutMapping("/{customerId}")
		public ResponseEntity<CustomerDto> updatePatient(@RequestBody CustomerDto customerDto, @PathVariable("customerId") Integer customerId){
			CustomerDto updatedCustomer = this.customerService.updateCustomer(customerDto,customerId);
			return ResponseEntity.ok(updatedCustomer);
		}
//	@PreAuthorize("hasRole('ADMIN_USER')")
	@DeleteMapping("/{customerId}")
	public ResponseEntity<ApiResponse > deletePatient(@PathVariable("customerId") Integer customerId){
		this.customerService.deleteCustomer(customerId);
		return new ResponseEntity(new ApiResponse("Customer Deleted Successfully" , true), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CustomerDto>> getAllCustomers(){
		return ResponseEntity.ok(this.customerService.getAllCustomers());
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getSingleCustomer(@PathVariable Integer customerId){
		return ResponseEntity.ok(this.customerService.getCustomerById(customerId));
	}
	

	@PostMapping("/customer")
	public void createCustomer(@RequestBody CustomerDto customer) {
		customerService.createCustomer(customer);
		
	}

	@GetMapping("/customer/{acctID}")
	public CustomerDto getCustomerInfo(@PathVariable int acctID) {
		return customerService.getCustomerById(acctID);
	}

	@DeleteMapping("/customer/{acctID}")
	public void deleteCustomer(@PathVariable int acctID) {
		customerService.deleteCustomer(acctID);
	}
}
