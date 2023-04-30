package com.onlinevoting.vote.dto;

import java.util.List;

import com.onlinevoting.vote.model.Party;
import com.onlinevoting.vote.model.Voter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class LoginResponse {

	int id;
	String name;
	String usename;
}
