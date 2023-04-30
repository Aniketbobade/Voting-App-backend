package com.onlinevoting.vote.dto;

import lombok.Data;

@Data
public class VoterSaveResp {

	private int voterId;
	private int adminId;
	private String voterName;
	private String voterUserName;
	private String AdminName;
}
