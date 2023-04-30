package com.onlinevoting.vote.dto;

import org.springframework.stereotype.Service;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Service
@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString
public class VoterRequest {
	
	private String adminName;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	
}
