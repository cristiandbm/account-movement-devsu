package com.devsu.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.entities.MovementEntity;

public interface IMovementRepository
		extends JpaRepository<MovementEntity, Long> {

	Optional<MovementEntity> findByIdTx(String idTx);

	List<MovementEntity> findByAccount_NumberAndMovementDate(
			String accountNumber, LocalDate date);
}
