package com.onlinevoting.vote.service;

import com.onlinevoting.vote.dto.VoterLoginReq;
import com.onlinevoting.vote.dto.VoterLoginResp;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinevoting.vote.dto.VoterRequest;
import com.onlinevoting.vote.dto.VoterSaveResp;
import com.onlinevoting.vote.model.Voter;

@Service
public interface VoterService {

	public ResponseEntity<VoterSaveResp> saveVoter(VoterRequest voterRequest);

    ResponseEntity<VoterLoginResp> loginVoter(VoterLoginReq voterLoginReq);
}
