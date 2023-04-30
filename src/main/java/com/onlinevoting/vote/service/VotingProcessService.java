package com.onlinevoting.vote.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinevoting.vote.dto.VotingProcessRequest;
import com.onlinevoting.vote.dto.VotingResponse;
import com.onlinevoting.vote.model.Party;

@Service
public interface VotingProcessService {

	
	
	public List<String> listParty(int id);
	
	public ResponseEntity<VotingResponse> votingProcess(VotingProcessRequest votingProcessRequest);
}
