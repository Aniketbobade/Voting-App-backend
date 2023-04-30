package com.onlinevoting.vote.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name ="Voter_details")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString
public class Voter{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int voterId;
	String firstName;
	String lastName;
	boolean isVoted;
	boolean isActive;
	
	 	@Column(name = "created_at")
	    private LocalDateTime createdAt;
	    
	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt;
	    
	    @PrePersist
	    protected void onCreate() {
	        this.createdAt = LocalDateTime.now();
	        this.updatedAt = LocalDateTime.now();
	    }
	    
	    @PreUpdate
	    protected void onUpdate() {
	        this.updatedAt = LocalDateTime.now();
	    }
	
	
	 @ManyToOne
	 @JsonManagedReference
	 private Admin admin;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "voter_id", referencedColumnName = "voterId")
	 private VoterLogin voterLogin;
}
