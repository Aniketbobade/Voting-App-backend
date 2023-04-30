package com.onlinevoting.vote.dto;

import lombok.Data;

@Data
public class VoterLoginReq {
    private String username;
    private String password;
}
