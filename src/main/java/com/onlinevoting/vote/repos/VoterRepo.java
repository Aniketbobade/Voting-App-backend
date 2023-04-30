package com.onlinevoting.vote.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlinevoting.vote.model.Admin;
import com.onlinevoting.vote.model.Voter;

@Repository
public interface VoterRepo extends JpaRepository<Voter, Integer> {

	List<Voter> findByAdminAndIsActive(Admin admin, boolean b);

//	@Query("select u From Voter u Where u.admin.adminId=:n And u.isActive=false")
//	List<Voter> findByAdminId(@Param("n") int adminId);
}
