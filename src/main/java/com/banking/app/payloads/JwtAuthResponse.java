package com.banking.app.payloads;

import com.banking.app.entities.Accounts;

import lombok.Data;

@Data
public class JwtAuthResponse {
	private String token;
	
	private CustomerDto user;
	
	private Accounts account;

	public JwtAuthResponse(String token, CustomerDto user, Accounts account) {
		super();
		this.token = token;
		this.user = user;
		this.account=account;
	}

	public CustomerDto getUser() {
		return user;
	}

	public Accounts getAccount() {
		return account;
	}

	public void setAccount(Accounts account) {
		this.account = account;
	}

	public void setUser(CustomerDto user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtAuthResponse(String token) {
		super();
		this.token = token;
	}

	public JwtAuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
