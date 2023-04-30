package com.onlinevoting.vote.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinevoting.vote.dto.AdminLoginRequest;
import com.onlinevoting.vote.dto.AdminRequest;
import com.onlinevoting.vote.dto.AdminSaveResp;
import com.onlinevoting.vote.dto.LoginResponse;
import com.onlinevoting.vote.dto.PendingValidationList;
import com.onlinevoting.vote.dto.VerificationRequest;
import com.onlinevoting.vote.dto.VerificationResponse;
import com.onlinevoting.vote.model.Admin;
import com.onlinevoting.vote.model.AdminLogin;
import com.onlinevoting.vote.model.Voter;
import com.onlinevoting.vote.repos.AdminLoginRepo;
import com.onlinevoting.vote.repos.AdminRepo;
import com.onlinevoting.vote.repos.VoterRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AdminImp implements AdminService {

	@Autowired
	AdminRepo adminRepo;

	@Autowired
	AdminLoginRepo adminLoginRepo;
	
	@Autowired
	VoterRepo voterRepo;

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<AdminSaveResp> saveAdmin(AdminRequest adminRequest) {
		Admin admin = new Admin();
		admin.setAdminName(adminRequest.getAdminName());
		adminRepo.save(admin);
		AdminLogin adminLogin = new AdminLogin();
		adminLogin.setUsername(adminRequest.getUserName());
		adminLogin.setPassword(adminRequest.getPassword());
		adminLogin.setAdmin(admin);
		adminLoginRepo.save(adminLogin);
		
		AdminSaveResp adminSaveResp = new AdminSaveResp();
		adminSaveResp.setAdminId(admin.getAdminId());
		adminSaveResp.setAdminName(admin.getAdminName());
		adminSaveResp.setAdminUsername(adminLogin.getUsername());
		return ResponseEntity.ok(adminSaveResp);
	}

	public ResponseEntity<LoginResponse> loginAdmin(AdminLoginRequest adminLoginRequest) {
		AdminLogin optionalAdmin = adminLoginRepo.findByUsernameAndPassword(adminLoginRequest.getUserName(),
				adminLoginRequest.getPassword());
		LoginResponse response=new LoginResponse();
		if (optionalAdmin.getUsername() != null) {
			Optional<Admin> admin= adminRepo.findById(optionalAdmin.getAdmin().getAdminId());
			if(admin.isPresent()) {
				response.setId(admin.get().getAdminId());
				response.setName(admin.get().getAdminName());
				response.setUsename((admin.get().getAdminLogin().getUsername()));
				return ResponseEntity.ok(response);
			}
			
		}
		
		return ResponseEntity.badRequest().body(null);
	}

	@Override
	 @Transactional
	public List<PendingValidationList> pendingList(int id) {
		
		 Admin admin = adminRepo.findById(id)
		            .orElseThrow(() -> new RuntimeException("Admin not found"));
		    List<Voter> inactiveVoters = voterRepo.findByAdminAndIsActive(admin, false);
		  List<PendingValidationList> list=new ArrayList<>();
		  
		  
		  for (Voter voter: inactiveVoters) {
			  PendingValidationList temp= new PendingValidationList();
			  temp.setVoterId(voter.getVoterId());
			  temp.setFirstname(voter.getFirstName());
			  temp.setLastName(voter.getLastName());
			  temp.setActive(voter.isActive());
			  
			  list.add(temp);
		}
		  return list;
		  
	}

	@Override
	public ResponseEntity<VerificationResponse> verication(VerificationRequest verificationRequest) {
		Optional<Voter> optionalVoter=voterRepo.findById(verificationRequest.getVoterId());
		VerificationResponse response=new VerificationResponse();
		
		if(optionalVoter.isEmpty()) {
			response.setSuccess(false);
			response.setVoterId(verificationRequest.getVoterId());
			response.setMessage("Voter ID not found");
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
			Voter voter=optionalVoter.get();
			
			if(verificationRequest.getIsOkay()==1) {
				voter.setActive(true);
				voterRepo.save(voter);
				response.setSuccess(true);
				response.setVoterId(voter.getVoterId());
				response.setVoterName(voter.getFirstName()+" "+voter.getLastName());
				response.setMessage("verification done succefully");
				return ResponseEntity.ok(response);
			}else {
				voter.setActive(false);
				voterRepo.save(voter);
				response.setSuccess(false);
				response.setVoterId(voter.getVoterId());
				response.setVoterName(voter.getFirstName()+" "+voter.getLastName());
				response.setMessage("Vefication faild because of inproper information");
				return ResponseEntity.ok(response);
			}
		
	}

	@Override
	public List<String> getAllAdmin() {
		// TODO Auto-generated method stub
		return adminRepo.findAll().stream().map(Admin::getAdminName).collect(Collectors.toList());	
	}


}
