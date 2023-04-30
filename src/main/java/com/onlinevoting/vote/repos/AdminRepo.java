package com.onlinevoting.vote.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinevoting.vote.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

	Optional<Admin> findByAdminName(String string);

	
}
