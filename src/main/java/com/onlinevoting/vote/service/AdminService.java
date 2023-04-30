package com.onlinevoting.vote.service;

import java.util.List;
import java.util.Set;

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

import jakarta.transaction.Transactional;

@Service
public interface AdminService {

	public ResponseEntity<AdminSaveResp> saveAdmin(AdminRequest adminRequestd);
	
	public ResponseEntity<LoginResponse> loginAdmin(AdminLoginRequest adminLoginRequest);
	
	 
	public List<PendingValidationList> pendingList(int id);
	
	public ResponseEntity<VerificationResponse> verication(VerificationRequest verificationRequest);

	public List<String> getAllAdmin();
}
