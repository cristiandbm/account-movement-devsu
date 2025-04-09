package com.devsu.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.devsu.dto.Movement;
import com.devsu.exception.BalanceException;

public interface IMovementService {

	Movement createMovement(Movement movement)throws NotFoundException, BalanceException;

	Movement updateMovement(String idTx, String accountNumber, Movement movement);

	List<Movement> findMovement(String accountNumber, LocalDate date);

}
