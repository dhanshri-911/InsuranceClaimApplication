package com.example.insuranceclaimapplication.service;

import com.example.insuranceclaimapplication.dto.LoginDTO;
import com.example.insuranceclaimapplication.dto.UserDTO;
import com.example.insuranceclaimapplication.model.User;

public interface IUserService {
    Object registerUser(UserDTO userDTO);

    Object showAll();

    Object findById(Long id);

    Object update(Long id, UserDTO userDTO);

    String remove(Long id);

    User login(LoginDTO loginDTO);

    Boolean verifyOtp(String username, String otp);

    Boolean isVerified(String username);



    Object getUserByDate(String date1, String date2);

    Object getuserByHealthCondition(String healthcondition);
}
