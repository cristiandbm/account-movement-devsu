package com.devsu.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.dto.Report;
import com.devsu.entities.AccountEntity;
import com.devsu.mapper.AccountMapper;
import com.devsu.mapper.MovementMapper;
import com.devsu.repository.IAccountRepository;
import com.devsu.service.IReportService;

@Service
public class ReportService implements IReportService {

	@Autowired
	private IAccountRepository accountRepository;

	@Override
	public List<Report> getReport(String clientId, LocalDate starDate,
			LocalDate endDate) {
		System.out.println(clientId + " " + starDate + " " + endDate);
		List<AccountEntity> accounts = accountRepository
				.findByClientIdAndMovements_MovementDateBetween(clientId,
						starDate, endDate);
		
		List<Report> reports = accounts.stream().map(account -> 
			Report.builder()
			.account(AccountMapper.AccountEntityToAccountDto(account))
			.movements(MovementMapper.listMovementEntityToListMovementDto(account.getMovements()))
			.build()).collect(Collectors.toList());
		

		return reports;
	}

}
