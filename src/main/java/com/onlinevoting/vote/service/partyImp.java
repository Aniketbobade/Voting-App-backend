package com.onlinevoting.vote.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinevoting.vote.dto.PartyRequest;
import com.onlinevoting.vote.dto.PartySaveRes;
import com.onlinevoting.vote.model.Admin;
import com.onlinevoting.vote.model.Party;
import com.onlinevoting.vote.repos.AdminRepo;
import com.onlinevoting.vote.repos.PartyRepo;
@Service
public class partyImp implements PartyService{
	
	@Autowired
	PartyRepo partyRepo;
	
	
	@Autowired
	AdminRepo adminRepo;

	@Override
	public ResponseEntity<PartySaveRes> save(PartyRequest partyRequest) {
		Optional<Admin> optionalAdmin= adminRepo.findById(partyRequest.getAdminId());
		PartySaveRes res=new PartySaveRes();
		if(optionalAdmin.isPresent()) {
			Admin admin= optionalAdmin.get();
			Party party=new Party();
			party.setPartyName(partyRequest.getPartyName());
			party.setVoteCount(0);
			party.setAdmin(admin);
			partyRepo.save(party);
			res.setMessage("Party saved successfully.");
			res.setStatus(true);
			res.setPartyId(party.getPartyId());
			res.setPartyName(party.getPartyName());
			return ResponseEntity.ok(res);
		}else {
			res.setMessage("Party not saved");
			res.setStatus(false);
			return ResponseEntity.badRequest().body(res);
		}
		
	}

}
