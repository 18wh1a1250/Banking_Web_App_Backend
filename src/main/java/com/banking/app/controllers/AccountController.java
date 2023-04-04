package com.banking.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.entities.Accounts;
import com.banking.app.entities.Logger;
import com.banking.app.payloads.CustomerDto;
import com.banking.app.services.impl.AccountService;

@RestController
public class AccountController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private LoggerController loggerController;

	
	@PostMapping("/account")
	public ResponseEntity<Accounts> createAccount(@Valid @RequestBody Accounts accounts){
		Accounts acctounts = this.accountService.createAccount(accounts);
		return new ResponseEntity<>(accounts, HttpStatus.CREATED);
		}
	@GetMapping("/{acctID}/balance")
	public int getBalance(@PathVariable int acctID) {
		return accountService.getBalance(acctID);
	}
	@PutMapping("/{acctID}/deposit/{amount}")
	public void depositAmount(@PathVariable int acctID, @PathVariable int amount) {
		int initBal = getBalance(acctID);
		accountService.depositAmount(acctID, amount);
		Logger logger = new Logger(acctID, "Deposited", "Success", initBal, initBal + amount);
		loggerController.addLog(logger);
	}

	// withdrawAmount
	@PutMapping("/{acctID}/withdraw/{amount}")
	public void withdrawAmount(@PathVariable int acctID, @PathVariable int amount) {
		int initBal = getBalance(acctID);
		accountService.withdrawAmount(acctID, amount);
		Logger logger = new Logger(acctID, "Withdrawn", "Success", initBal, initBal - amount);
		loggerController.addLog(logger);
	}

	// transferAmount
	@PutMapping("/{acctID}/transfer/{destAcctID}/{amount}")
	public void transferAmount(@PathVariable int acctID, @PathVariable int destAcctID, @PathVariable int amount) {
		int initBalSender = getBalance(acctID);
		int initBalReceiver = getBalance(destAcctID);
		accountService.transferAmount(acctID, destAcctID, amount);
		Logger loggerSender = new Logger(acctID, "Transferred", "Success", initBalSender, initBalSender - amount);
		loggerController.addLog(loggerSender);
		Logger loggerReceiver = new Logger(destAcctID, "Received", "Success", initBalReceiver,
				initBalReceiver + amount);
		loggerController.addLog(loggerReceiver);
	}

	// deleteAccount
	@DeleteMapping("/{acctID}")
	public void deleteAccount(@PathVariable int acctID) {
		accountService.deleteAccount(acctID);
		loggerController.deleteLog(acctID);
	}

	// getAccountInfo
	@GetMapping("account/{acctID}")
	public Accounts getAccountInfo(@PathVariable int acctID) {
		return accountService.getAccountInfo(acctID);
	}
	@GetMapping("/accounts")
	public List<Accounts> getAllAccounts(){
		return accountService.getAllAccounts();
	}
}
