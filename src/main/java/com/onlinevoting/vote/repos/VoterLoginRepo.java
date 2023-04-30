package com.onlinevoting.vote.repos;

import com.onlinevoting.vote.model.AdminLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinevoting.vote.model.VoterLogin;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface VoterLoginRepo extends JpaRepository<VoterLogin, Integer> {

    public Optional<VoterLogin> findByUserNameAndPassword(String username, String password);
}
