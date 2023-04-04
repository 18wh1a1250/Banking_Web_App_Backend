package com.banking.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.entities.Accounts;
import com.banking.app.entities.Customer;
import com.banking.app.payloads.CustomerDto;
import com.banking.app.repositories.AccountsRepository;

@Service
public class AccountService {
	@Autowired
	private AccountsRepository accountRepository;

	public Accounts createAccount(Accounts acct) {
		return accountRepository.save(acct);
	}

	public Accounts getAccountInfo(int acctID) {
		return accountRepository.findById(acctID).orElse(null);
	}

	public void deleteAccount(int acctID) {
		accountRepository.deleteById(acctID);
	}

	public int getBalance(int acctID) {
		return accountRepository.findBalanceByAcctID(acctID);
	}

	public void depositAmount(int acctID, int amount) {
		accountRepository.saveBalanceByAcctID(acctID, amount);
	}

	public void withdrawAmount(int acctID, int amount) {
		accountRepository.withdrawAmountByAcctID(acctID, amount);
	}

	public void transferAmount(int acctID, int destAcctID, int amount) {
		accountRepository.withdrawAmountByAcctID(acctID, amount);
		accountRepository.saveBalanceByAcctID(destAcctID, amount);
	}
	public List<Accounts> getAllAccounts() {
		List<Accounts> accounts = this.accountRepository.findAll();
		return accounts;
	}
}