package com.onlinevoting.vote.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinevoting.vote.model.Party;

public interface PartyRepo extends JpaRepository<Party, Integer> {

	

}
