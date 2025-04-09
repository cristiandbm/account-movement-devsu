package com.devsu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.dto.Account;
import com.devsu.service.IAccountService;

@RestController
@RequestMapping("cuentas")
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@PostMapping
	public ResponseEntity<String> createAccount(@RequestBody Account account) throws Exception {
		accountService.createAccount(account);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping
	public ResponseEntity<Account> findAccount(
			@RequestParam(name = "accountNumber", required = true) String accountNumber)
			throws NotFoundException {

		return new ResponseEntity<Account>(
				accountService.findAccount(accountNumber), HttpStatus.OK);
	}

	@PatchMapping("/{accountNumber}")
	public ResponseEntity<Account> updateAccount(
			@PathVariable(name = "accountNumber") String accountNumber,
			@RequestBody Account account) throws NotFoundException {

		return new ResponseEntity<Account>(
				accountService.updateAccount(accountNumber, account),
				HttpStatus.OK);
	}

}
