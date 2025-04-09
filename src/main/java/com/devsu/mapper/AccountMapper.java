package com.devsu.mapper;

import com.devsu.dto.Account;
import com.devsu.entities.AccountEntity;

public class AccountMapper {
	
	public static AccountEntity AccountDtoToAccountEntity(Account account) {
		return AccountEntity.builder()
				.number(account.getNumber())
				.openingBalance(account.getOpeningBalance())
				.type(account.getType())
				.status(account.getStatus())
				.clientId(account.getClientId())
				.build();
	}
	
	public static Account AccountEntityToAccountDto(AccountEntity account) {
		return Account.builder()
				.number(account.getNumber())
				.openingBalance(account.getOpeningBalance())
				.type(account.getType())
				.status(account.isStatus())
				.clientId(account.getClientId())
				.build();
	}

}
