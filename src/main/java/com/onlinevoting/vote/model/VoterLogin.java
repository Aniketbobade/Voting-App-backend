package com.onlinevoting.vote.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Entity
public class VoterLogin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int voterId;
	@Column(unique = true)
	private String userName;
	private String password;

	@OneToOne
    @JoinColumn(name = "voter_id")
    private Voter voter;
}
