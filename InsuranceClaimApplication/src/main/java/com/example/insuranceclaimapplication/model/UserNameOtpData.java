package com.example.insuranceclaimapplication.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserNameOtpData {
    @Id
    private String emailId;
    private String otp;

    public UserNameOtpData(String emailId, String otp) {
        this.emailId = emailId;
        this.otp = otp;
    }

    public UserNameOtpData() {
    }
}