package com.example.insuranceclaimapplication.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String password;
    private String fullName;
    private String permanentAddress;
    private String temporaryAddress;
    private Long mobileNumber;
    private int age;
    private String occupation;
    private String familyBackground;
    private String KYC_File;
    private String healthCondition;
    private String vehicleData;
    private String userRole;
    private String emailId;
    private String registerdDate;
    private String updatedDate;
    private String username;
}
