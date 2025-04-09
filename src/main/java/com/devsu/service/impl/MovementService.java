package com.devsu.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.devsu.dto.Movement;
import com.devsu.entities.AccountEntity;
import com.devsu.entities.MovementEntity;
import com.devsu.enumeration.MovementType;
import com.devsu.exception.BalanceException;
import com.devsu.mapper.MovementMapper;
import com.devsu.repository.IAccountRepository;
import com.devsu.repository.IMovementRepository;
import com.devsu.service.IMovementService;

@Service
public class MovementService implements IMovementService {

	private static final String SALDO_NO_DISPONIBLE = "Saldo no disponible";

	@Autowired
	private IMovementRepository movementRepository;

	@Autowired
	private IAccountRepository accountRepository;

	@Override
	public Movement createMovement(Movement movement) throws NotFoundException, BalanceException {

		MovementEntity movementToSaved = MovementMapper
				.movementDtoToMovementEntity(movement);

		AccountEntity account = accountRepository
				.findById(movement.getAccountNumber())
				.orElseThrow(() -> new NotFoundException());

		Double newBalance = 0.0;

		if (MovementType.ABONO.toString().equals(movement.getType())) {
			newBalance = account.getOpeningBalance() + movement.getValueTx();
		} else if (MovementType.RETIRO.toString().equals(movement.getType())) {
			newBalance = account.getOpeningBalance() - movement.getValueTx();
			if (newBalance < 0.0) {
				throw new BalanceException(SALDO_NO_DISPONIBLE);
			}
		} else {
			throw new NotFoundException();
		}

		movementToSaved.setOpeningBalance(newBalance);

		MovementEntity movementSaved = movementRepository.save(movementToSaved);

		account.setOpeningBalance(newBalance);
		accountRepository.save(account);
		return MovementMapper.movementEntityToMovementDto(movementSaved);
	}

	@Override
	public Movement updateMovement(String idTx, String accountNumber,
			Movement movement) {

		Optional<MovementEntity> opMovementFound = movementRepository
				.findByIdTx(idTx);

		if (opMovementFound.isPresent()) {
			MovementEntity movementFound = opMovementFound.get();
			movementFound.setStatus(movement.isStatus());
			return MovementMapper.movementEntityToMovementDto(
					movementRepository.save(movementFound));
		}

		return null;
	}

	@Override
	public List<Movement> findMovement(String accountNumber, LocalDate date) {
		List<MovementEntity> movements = movementRepository
				.findByAccount_NumberAndMovementDate(accountNumber, date);
		return MovementMapper.listMovementEntityToListMovementDto(movements);
	}

}
