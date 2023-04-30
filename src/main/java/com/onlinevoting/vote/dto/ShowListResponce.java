package com.onlinevoting.vote.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class ShowListResponce {
	private int partyId;
	private String partyName;
	private Map<Integer, String> partylist;

}
