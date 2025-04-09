package com.devsu.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementEntity {
 
	@Id
	@Column
	private String idTx;
	
	private String type;
	
	@Column(name = "opening_balance")
	private Double openingBalance;
	
	@Column(name = "value_tx")
	private Double valueTx;
	
	private boolean status;
	
	@Column(name ="movement_date")
	private LocalDate movementDate;
	
	@ManyToOne
	@JoinColumn(name = "number", nullable = false)
	private AccountEntity account;
	
	
}
