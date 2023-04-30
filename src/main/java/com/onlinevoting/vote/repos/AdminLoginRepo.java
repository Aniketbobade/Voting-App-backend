package com.onlinevoting.vote.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.onlinevoting.vote.model.Admin;
import com.onlinevoting.vote.model.AdminLogin;

@EnableJpaRepositories
@Repository
public interface AdminLoginRepo extends JpaRepository<AdminLogin, Integer> {
	
	public AdminLogin findByUsernameAndPassword(String username,String password);
	
	public AdminLogin findByUsername(String username);

}
