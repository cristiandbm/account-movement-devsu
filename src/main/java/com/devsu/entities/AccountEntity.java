package com.devsu.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

	@Id
	@Column
	private String number;

	private String type;

	@Column(name = "opening_balance")
	private Double openingBalance;

	private boolean status;

	@Column(name = "client_id")
	private String clientId;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<MovementEntity> movements;

}
