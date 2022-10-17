package com.example.insuranceclaimapplication.model;
import com.example.insuranceclaimapplication.dto.LoginDTO;
import com.example.insuranceclaimapplication.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private  String username;
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
    private String registerdDate;
    private String updatedDate;
    private String userRole;
    private String emailId;
    public boolean verified;

    public User(UserDTO userDto) {
        this.fullName = userDto.getUsername();
        this.fullName = userDto.getFullName();
        this.password = userDto.getPassword();
        this.userRole = userDto.getUserRole();
        this.emailId = userDto.getEmailId();
        this.updatedDate = userDto.getUpdatedDate();
        this.registerdDate = userDto.getRegisterdDate();
        this.vehicleData = userDto.getVehicleData();
        this.healthCondition = userDto.getHealthCondition();
        this.KYC_File = userDto.getKYC_File();
        this.familyBackground = userDto.getFamilyBackground();
        this.occupation = userDto.getOccupation();
        this.occupation = userDto.getOccupation();
        this.age = userDto.getAge();
        this.mobileNumber = userDto.getMobileNumber();
        this.temporaryAddress = userDto.getTemporaryAddress();
        this.permanentAddress = userDto.getPermanentAddress();
    }

    public User(Long id, UserDTO userDto) {
        this.username = userDto.getUsername();
        this.fullName = userDto.getFullName();
        this.password = userDto.getPassword();
        this.userRole = userDto.getUserRole();
        this.emailId = userDto.getEmailId();
        this.updatedDate = userDto.getUpdatedDate();
        this.registerdDate = userDto.getRegisterdDate();
        this.vehicleData = userDto.getVehicleData();
        this.healthCondition = userDto.getHealthCondition();
        this.KYC_File = userDto.getKYC_File();
        this.familyBackground = userDto.getFamilyBackground();
        this.occupation = userDto.getOccupation();
        this.occupation = userDto.getOccupation();
        this.age = userDto.getAge();
        this.mobileNumber = userDto.getMobileNumber();
        this.temporaryAddress = userDto.getTemporaryAddress();
        this.permanentAddress = userDto.getPermanentAddress();
    }

    public User(LoginDTO loginDTO) {
        this.userRole=loginDTO.getUserRole();
        this.emailId=loginDTO.getEmailId();
        this.password=loginDTO.getPassword();
    }
}
