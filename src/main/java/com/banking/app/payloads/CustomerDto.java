package com.banking.app.payloads;
import java.util.Date;
import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {
	private int customerId;
	
	@NotEmpty
	@Size(min = 4, message="Username should not be less than 4 characters")
	private String name;
	
	@NotEmpty
	@Size(min=11, max=17, message="Enter the account number")
	private Long accountNo;
	
	@NotEmpty
	@Size(min=1, max=2, message="Enter account Id")
	private int acctID;
	
	@NotEmpty
	@Size(min=11, max=11, message="Enter the IFSC Code")
	private String ifscCode;
	
	@Email(message = "Email address is not valid")
	@NotEmpty(message="Email is required")
	private String email;
	
	@NotEmpty()
	@Size(min=10,max=10, message="Mobile no should be of 10 characters")
	private String mobileno;
	
	@NotEmpty()
	@Size(min = 8, max = 10, message="Password should not be less than 8 characters")
	private String password;
	
	    
	public int getAcctID() {
		return acctID;
	}
	public void setAcctID(int acctID) {
		this.acctID = acctID;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	private String city;
	private String state;
	private String country;
	
//	    @Transient
//		private List<Accounts> account;
//	    
//	public List<Accounts> getAccount() {
//			return account;
//		}
//		public void setAccount(List<Accounts> account) {
//			this.account = account;
//		}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	public CustomerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerDto(int customerId,
			@NotEmpty @Size(min = 4, message = "Username should not be less than 4 characters") String name,
			@NotEmpty @Size(min = 11, max = 17, message = "Enter the account number") Long accountNo,
			@NotEmpty @Size(min = 1, max = 2, message = "Enter account Id") int acctID,
			@NotEmpty @Size(min = 11, max = 11, message = "Enter the IFSC Code") String ifscCode,
			@Email(message = "Email address is not valid") String email,
			@NotEmpty @Size(min = 10, max = 10, message = "Mobile no should be of 10 characters") String mobileno,
			@NotEmpty @Size(min = 8, max = 10, message = "Password should not be less than 8 characters") String password,
			String city, String state, String country, Set<RoleDto> roles) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.accountNo = accountNo;
		this.acctID = acctID;
		this.ifscCode = ifscCode;
		this.email = email;
		this.mobileno = mobileno;
		this.password = password;
		this.city = city;
		this.state = state;
		this.country = country;
		this.roles = roles;
	}
	private Set<RoleDto> roles = new HashSet<>();
	
	public Set<RoleDto> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	
	
}
