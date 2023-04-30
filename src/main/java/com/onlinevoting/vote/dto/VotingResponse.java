package com.onlinevoting.vote.dto;

import lombok.Data;

@Data
public class VotingResponse {
	boolean status;
	String message;
	int voterId;
	String voterName;
}
