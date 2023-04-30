package com.onlinevoting.vote.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinevoting.vote.dto.PartyRequest;
import com.onlinevoting.vote.dto.PartySaveRes;

@Service
public interface PartyService {

	public ResponseEntity<PartySaveRes> save(PartyRequest partyRequest);
}
