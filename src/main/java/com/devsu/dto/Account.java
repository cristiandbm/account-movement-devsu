package com.devsu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	private String number;
	
	private String type;
	
	private Double openingBalance;
	
	private Boolean status;
	
	private String clientId;
}
