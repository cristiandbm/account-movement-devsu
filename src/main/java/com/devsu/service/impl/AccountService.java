package com.devsu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.devsu.dto.Account;
import com.devsu.entities.AccountEntity;
import com.devsu.enumeration.AccountType;
import com.devsu.mapper.AccountMapper;
import com.devsu.repository.IAccountRepository;
import com.devsu.service.IAccountService;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private IAccountRepository accountRepository;

	@Override
	public Account createAccount(Account account) throws Exception {
		if (!AccountType.COL.getAccountType().contains(account.getType())) {
			throw new Exception("Tipo de cuenta no valido");
		}
		AccountEntity accountToSave = AccountMapper
				.AccountDtoToAccountEntity(account);
		AccountEntity accountSaved = accountRepository.save(accountToSave);
		return AccountMapper.AccountEntityToAccountDto(accountSaved);

	}

	@Override
	public Account updateAccount(String accountNumber, Account account)
			throws NotFoundException {
		AccountEntity accountFound = accountRepository.findById(accountNumber)
				.orElseThrow(() -> new NotFoundException());

		if (account.getStatus() != null) {
			accountFound.setStatus(account.getStatus());
		}

		return AccountMapper.AccountEntityToAccountDto(
				accountRepository.save(accountFound));

	}

	@Override
	public Account findAccount(String accountNumber) throws NotFoundException {
		AccountEntity accountFound = accountRepository.findById(accountNumber)
				.orElseThrow(() -> new NotFoundException());

		return AccountMapper.AccountEntityToAccountDto(accountFound);

	}

}
