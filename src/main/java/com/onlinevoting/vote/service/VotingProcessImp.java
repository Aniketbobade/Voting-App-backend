package com.onlinevoting.vote.service;

import java.util.*;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinevoting.vote.dto.VotingProcessRequest;
import com.onlinevoting.vote.dto.VotingResponse;
import com.onlinevoting.vote.model.Admin;
import com.onlinevoting.vote.model.Party;
import com.onlinevoting.vote.model.Voter;
import com.onlinevoting.vote.repos.AdminRepo;
import com.onlinevoting.vote.repos.PartyRepo;
import com.onlinevoting.vote.repos.VoterRepo;

@Service
public class VotingProcessImp implements VotingProcessService {

	@Autowired
	VoterRepo voterRepo;

	@Autowired
	AdminRepo adminRepo;

	@Autowired
	PartyRepo partyRepo;

	public ResponseEntity<VotingResponse> votingProcess(VotingProcessRequest votingProcessRequest) {
		Optional<Voter> optionalVoter = voterRepo.findById(votingProcessRequest.getVoterId());
		VotingResponse res=new VotingResponse();
		if (optionalVoter.isEmpty()) {
			res.setMessage("voter not found");
			res.setStatus(false);
			return ResponseEntity.badRequest().body(res);
		}

		Voter voter = optionalVoter.get();
		if (!voter.isActive()) {
			res.setMessage("your verification is pending");
			res.setVoterId(voter.getVoterId());
			res.setVoterName(voter.getFirstName()+" "+voter.getLastName());
			res.setStatus(false);
			return ResponseEntity.badRequest().body(res);
		}

		if (voter.isVoted()) {
			res.setMessage("You have already voted");
			res.setStatus(false);
			res.setVoterId(voter.getVoterId());
			res.setVoterName(voter.getFirstName()+" "+voter.getLastName());
			return ResponseEntity.badRequest().body(res);
		}

		Admin admin = adminRepo.getReferenceById(voter.getAdmin().getAdminId());
		Party selectedParty = partyRepo.getReferenceById(votingProcessRequest.getPartyId());

		if (selectedParty.getAdmin() != admin) {
			res.setMessage("You are not authorized to vote for this party");
			res.setStatus(false);
			res.setVoterId(voter.getVoterId());
			res.setVoterName(voter.getFirstName()+" "+voter.getLastName());
			return ResponseEntity.badRequest().body(res);
		}

		int voteCount = selectedParty.getVoteCount() + 1;
		selectedParty.setVoteCount(voteCount);
		partyRepo.save(selectedParty);
		voter.setVoted(true);
		voterRepo.save(voter);
		res.setMessage("Voting successfully done");
		res.setStatus(true);
		res.setVoterId(voter.getVoterId());
		res.setVoterName(voter.getFirstName()+" "+voter.getLastName());
		return ResponseEntity.ok(res);
	}

	@Override
	public List<String> listParty(int id) {
		// TODO Auto-generated method stub
		Optional<Voter> optinalVoter = voterRepo.findById(id);
		if (optinalVoter.isPresent()) {
			Voter voter = optinalVoter.get();
			Admin admin = voter.getAdmin();
			List<Party> party = admin.getPartys();
			List<String> list= new ArrayList<>();

			for (Party party2 : party) {

				list.add(party2.getPartyName());
			}
			return list;
		}
		return null;
	}

}
