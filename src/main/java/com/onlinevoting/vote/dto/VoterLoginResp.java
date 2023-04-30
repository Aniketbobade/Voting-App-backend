package com.onlinevoting.vote.dto;

import lombok.Data;

@Data
public class VoterLoginResp {
    private int adminId;
    private int voterId;
    private String adminName;
    private String voterName;
    private String username;
    private boolean requestStatus;
    private boolean isVoted;
}
