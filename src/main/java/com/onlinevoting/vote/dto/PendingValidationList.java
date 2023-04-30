package com.onlinevoting.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class PendingValidationList {
	int VoterId;
	String Firstname;
	String LastName;
	boolean isActive=false;
	

}
