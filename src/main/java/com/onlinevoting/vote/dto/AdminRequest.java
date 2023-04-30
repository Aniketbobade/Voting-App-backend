package com.onlinevoting.vote.dto;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Service
public class AdminRequest {

	private String adminName;
	private String userName;
	private String password;
	
}
