package com.devsu.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.devsu.dto.Account;

public interface IAccountService {

	Account createAccount(Account account) throws Exception;

	Account updateAccount(String accountNumber, Account account) throws NotFoundException;

	Account findAccount(String accountNumber)throws NotFoundException;

}
