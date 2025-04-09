package com.devsu.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.devsu.dto.Movement;
import com.devsu.entities.AccountEntity;
import com.devsu.entities.MovementEntity;

public class MovementMapper {

	public static MovementEntity movementDtoToMovementEntity(
			Movement movement) {
		return MovementEntity.builder().idTx(movement.getAccountNumber())
				.type(movement.getType())
				.openingBalance(movement.getOpeningBalance())
				.valueTx(movement.getValueTx()).status(movement.isStatus())
				.account(AccountEntity.builder().number(movement.getAccountNumber())
						.build())
				.movementDate(LocalDate.now())
				.idTx(UUID.randomUUID().toString())
				.build();
	}

	public static Movement movementEntityToMovementDto(
			MovementEntity movement) {
		return Movement.builder().accountNumber(movement.getIdTx())
				.type(movement.getType())
				.openingBalance(movement.getOpeningBalance())
				.valueTx(movement.getValueTx()).status(movement.isStatus())
				.accountNumber(movement.getAccount().getNumber()).build();
	}

	public static List<Movement> listMovementEntityToListMovementDto(
			List<MovementEntity> movements) {
		return movements.stream()
				.map(MovementMapper::movementEntityToMovementDto)
				.collect(Collectors.toList());
	}
}
