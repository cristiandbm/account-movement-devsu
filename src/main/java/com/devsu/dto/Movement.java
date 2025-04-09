package com.devsu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movement {
	
	private String type;
	
	private Double openingBalance;
	
	private Double valueTx;
	
	private boolean status;
	
	private String accountNumber;
}
