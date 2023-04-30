package com.onlinevoting.vote.dto;

import lombok.Data;

@Data
public class PartySaveRes {

	int partyId;
	String partyName;
	String message;
	boolean status;
}
