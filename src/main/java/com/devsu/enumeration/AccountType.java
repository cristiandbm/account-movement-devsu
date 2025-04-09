package com.devsu.enumeration;

import java.util.List;

public enum AccountType {
	COL(List.of("AHORROS", "CORRIENTE")),
	MEX(List.of("DEBITO", "CREDITO", "NOMINA"));

	private final List<String> accountType;

	AccountType(List<String> accountType) {
		this.accountType = accountType;
	}

	public List<String> getAccountType() {
		return accountType;
	}
}
