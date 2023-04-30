package com.onlinevoting.vote.service;

import java.util.ArrayList;
import java.util.Optional;

import com.onlinevoting.vote.dto.VoterLoginReq;
import com.onlinevoting.vote.dto.VoterLoginResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinevoting.vote.dto.VoterRequest;
import com.onlinevoting.vote.dto.VoterSaveResp;
import com.onlinevoting.vote.model.Admin;
import com.onlinevoting.vote.model.Party;
import com.onlinevoting.vote.model.Voter;
import com.onlinevoting.vote.model.VoterLogin;
import com.onlinevoting.vote.repos.AdminRepo;
import com.onlinevoting.vote.repos.VoterLoginRepo;
import com.onlinevoting.vote.repos.VoterRepo;

@Service
public class VoterServiceImp implements VoterService {
	
	@Autowired
	VoterRepo voterRepo;
	
	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	VoterLoginRepo voterLoginRepo;
	
	@Override
	public ResponseEntity<VoterSaveResp> saveVoter(VoterRequest voterRequest) {
		// TODO Auto-generated method stub
		Optional<Admin> optionalAdmin = adminRepo.findByAdminName(voterRequest.getAdminName());
//		System.out.println(voterRequest.getAdminId());
		if(optionalAdmin.isPresent()) {
			Admin admin=optionalAdmin.get();
			Voter voter=new Voter();
			voter.setFirstName(voterRequest.getFirstName());
			voter.setLastName(voterRequest.getLastName());
			voter.setAdmin(admin);
			voterRepo.save(voter);
			
			VoterLogin voterlogin= new VoterLogin();
			voterlogin.setUserName(voterRequest.getUsername());
			voterlogin.setPassword(voterRequest.getPassword());
			voterlogin.setVoter(voter);
			voterLoginRepo.save(voterlogin);
			
//			return the response
			VoterSaveResp voterSaveResp=new VoterSaveResp();
			voterSaveResp.setAdminId(admin.getAdminId());
			voterSaveResp.setAdminName(admin.getAdminName());
			voterSaveResp.setVoterId(voter.getVoterId());
			voterSaveResp.setVoterName(voter.getFirstName()+" "+voterRequest.getLastName());
			voterSaveResp.setVoterUserName(voterlogin.getUserName());
			
			return ResponseEntity.ok(voterSaveResp);	
		}
		return ResponseEntity.badRequest().body(null);
	}

	@Override
	public ResponseEntity<VoterLoginResp> loginVoter(VoterLoginReq voterLoginReq) {
		Optional<VoterLogin> OptionalVoterLogin=voterLoginRepo.
				findByUserNameAndPassword(voterLoginReq.getUsername(),voterLoginReq.getPassword());
		if(OptionalVoterLogin.isEmpty()){
			VoterLoginResp voterLoginResp= new VoterLoginResp();
			voterLoginResp.setRequestStatus(false);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(voterLoginResp);
		}
		VoterLogin voterLogin=OptionalVoterLogin.get();
		Optional<Voter> optionalVoter=voterRepo.findById(voterLogin.getVoterId());
		Voter voter=optionalVoter.get();
		VoterLoginResp voterLoginResp=new VoterLoginResp();
		voterLoginResp.setVoterName(voter.getFirstName()+" "+voter.getLastName());
		voterLoginResp.setVoterId(voter.getVoterId());
		voterLoginResp.setUsername(voterLogin.getUserName());
		voterLoginResp.setAdminId(voter.getAdmin().getAdminId());
		voterLoginResp.setAdminName(voter.getAdmin().getAdminName());
		voterLoginResp.setRequestStatus(true);
		voterLoginResp.setVoted(voter.isVoted());
		return ResponseEntity.ok(voterLoginResp);
	}


}
