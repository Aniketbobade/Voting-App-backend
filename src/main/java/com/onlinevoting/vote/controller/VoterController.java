package com.onlinevoting.vote.controller;

import com.onlinevoting.vote.dto.VoterLoginReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinevoting.vote.dto.VoterRequest;
import com.onlinevoting.vote.dto.VoterSaveResp;
import com.onlinevoting.vote.service.VoterService;

@RestController
@RequestMapping("/voter")
@CrossOrigin(origins = "http://localhost:3000")
public class VoterController {
	
	@Autowired
	VoterService voterService;
	
	@PostMapping("/save")
	public ResponseEntity<VoterSaveResp> saveAdmin(@RequestBody VoterRequest voterRequest) {
	
		return voterService.saveVoter(voterRequest);   
    }

	@PostMapping("/login")
	public Object loginVoter(@RequestBody VoterLoginReq voterLoginReq){

			return voterService.loginVoter(voterLoginReq);

	}
	
}
