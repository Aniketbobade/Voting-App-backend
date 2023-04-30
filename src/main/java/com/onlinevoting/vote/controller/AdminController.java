package com.onlinevoting.vote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinevoting.vote.dto.AdminLoginRequest;
import com.onlinevoting.vote.dto.AdminRequest;
import com.onlinevoting.vote.dto.AdminSaveResp;
import com.onlinevoting.vote.dto.LoginResponse;
import com.onlinevoting.vote.dto.PendingValidationList;
import com.onlinevoting.vote.dto.VerificationRequest;
import com.onlinevoting.vote.dto.VerificationResponse;
import com.onlinevoting.vote.model.Admin;
import com.onlinevoting.vote.model.AdminLogin;
import com.onlinevoting.vote.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping("/save")
	public ResponseEntity<AdminSaveResp> saveAdmin(@RequestBody AdminRequest adminRequest) {
		return adminService.saveAdmin(adminRequest);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginAdmin(@RequestBody AdminLoginRequest adminLoginRequest) {
			try {
				return adminService.loginAdmin(adminLoginRequest);
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		
	}

	@GetMapping("/{id}")
	public List<PendingValidationList> pendingValidation(@PathVariable int id) {
		return adminService.pendingList(id);
	}

	@PostMapping("/verification")
	public ResponseEntity<VerificationResponse> verification(@RequestBody VerificationRequest verificationRequest) {
		return adminService.verication(verificationRequest);
	}
	@GetMapping("/getAllAdmin")
	public List<String> getAllAdmin(){
		return adminService.getAllAdmin();
	}
}
