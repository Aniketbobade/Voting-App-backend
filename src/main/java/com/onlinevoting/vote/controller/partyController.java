package com.onlinevoting.vote.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinevoting.vote.dto.PartyRequest;
import com.onlinevoting.vote.dto.PartySaveRes;
import com.onlinevoting.vote.service.PartyService;

@RestController
@RequestMapping("/party")
public class partyController {
	
	@Autowired
	PartyService partyService;
	
	@PostMapping("/save")
	public ResponseEntity<PartySaveRes> save(@RequestBody PartyRequest partyRequest){
		return partyService.save(partyRequest);
	}
	
}
