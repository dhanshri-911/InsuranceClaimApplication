package com.example.insuranceclaimapplication.dto;

import lombok.Data;

@Data
public class UserNameOtpDTO {
    private String emailId;
    private String otp;

    public UserNameOtpDTO(String emailId, String otp) {
        this.otp=otp;
        this.emailId=emailId;
    }
}