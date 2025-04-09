package com.devsu.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.dto.Movement;
import com.devsu.exception.BalanceException;
import com.devsu.service.IMovementService;

@RestController
@RequestMapping("movimientos")
public class MovementController {

	@Autowired
	private IMovementService movementService;

	@PostMapping
	public ResponseEntity<String> createMovement(@RequestBody Movement movement)
			throws NotFoundException, BalanceException {
		movementService.createMovement(movement);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping
	public ResponseEntity<List<Movement>> findMovement(
			@RequestParam(name = "accountNumber") String accountNumber,
			@RequestParam(name = "date") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDate date) {

		List<Movement> response = movementService.findMovement(accountNumber,
				date);
		if (response.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(response);
	}

	@PatchMapping
	public ResponseEntity<String> updateMovement(
			@RequestParam(name = "id") String id,
			@RequestParam(name = "accountNumber") String accountNumber,
			@RequestBody Movement movement) {

		Movement movementUpdated = movementService.updateMovement(id,
				accountNumber, movement);

		if (movementUpdated == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok().build();
	}
}
