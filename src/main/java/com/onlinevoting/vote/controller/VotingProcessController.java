package com.onlinevoting.vote.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.onlinevoting.vote.dto.VotingProcessRequest;
import com.onlinevoting.vote.dto.VotingResponse;
import com.onlinevoting.vote.service.VotingProcessService;

@RestController
@RequestMapping("/voting")
@CrossOrigin(origins = "http://localhost:3000")
public class VotingProcessController {

	@Autowired
	VotingProcessService votingProcessService;
	
	@GetMapping("/list/{id}")
	public List<String> showList(@PathVariable int id){
		return votingProcessService.listParty(id);
	}
	
	@PostMapping("updateVote")
	public ResponseEntity<VotingResponse> updatingVoting
	(@RequestBody VotingProcessRequest votingProcessRequest){
		return votingProcessService.votingProcess(votingProcessRequest);
	}
}
