package com.devsu.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.entities.AccountEntity;

public interface IAccountRepository extends JpaRepository<AccountEntity, String> {

	List<AccountEntity> findByClientIdAndMovements_MovementDateBetween(String client, LocalDate starDate, LocalDate endDate);
}
