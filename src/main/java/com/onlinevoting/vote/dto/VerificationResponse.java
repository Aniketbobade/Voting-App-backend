package com.onlinevoting.vote.dto;

import lombok.Data;

@Data
public class VerificationResponse {

	int voterId;
	boolean success;
	String voterName;
	String message;

}
