package com.onlinevoting.vote.dto;

import java.util.List;

import com.onlinevoting.vote.model.Party;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class VotingProcessRequest {
	
	private int voterId;
	private int partyId;
	
}
