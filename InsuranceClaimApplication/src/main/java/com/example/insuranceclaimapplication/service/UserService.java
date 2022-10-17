package com.example.insuranceclaimapplication.service;
import com.example.insuranceclaimapplication.Repository.UserNameRepo;
import com.example.insuranceclaimapplication.Repository.UserRepo;
import com.example.insuranceclaimapplication.dto.LoginDTO;
import com.example.insuranceclaimapplication.dto.UserDTO;
import com.example.insuranceclaimapplication.exception.InsuranceExceptionHandler;
import com.example.insuranceclaimapplication.model.LoginData;
import com.example.insuranceclaimapplication.model.User;
import com.example.insuranceclaimapplication.model.UserNameOtpData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    UserNameRepo userNameRepo;


    @Override
    public Object registerUser(UserDTO userDTO) {
        User user = new User(userDTO);
        return userRepo.save(user);
    }

    @Override
    public Object showAll() {
        return userRepo.findAll();
    }

    @Override
    public Object findById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new InsuranceExceptionHandler("User  with id " + id + " does not exist in database..!"));
    }

    @Override
    public Object update(Long id, UserDTO userDTO) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()){
            User newUser = new User(id, userDTO);
            User user1 = userRepo.save(newUser);
            return user1;
        }
        throw (new InsuranceExceptionHandler("User Not Found"));
    }

    @Override
    public String remove(Long id) {
        userRepo.deleteById(id);
        return "Record Deleted Successfully...";
    }

    @Override
    public User login(LoginDTO loginDTO) {
        User user = new User(loginDTO);
        int otps = (int) Math.floor(Math.random() * 1000000);
        String otp = String.valueOf(otps);
        UserNameOtpData userNameOtp = new UserNameOtpData(loginDTO.getEmailId(), otp);;
        userNameRepo.save(userNameOtp);//otp is saved
        System.out.println("Mail has sent .....!!!!");
        emailSenderService.sendEmail(user.getEmailId(), "OTP for Login..", otp);
        return userRepo.save(user);//save user data
    }

    @Override
    public Boolean verifyOtp(String emailId, String otp) {
        UserNameOtpData serverOtp = userNameRepo.findByEmailId(emailId);

        if (otp == null)
            return false;
        if(!(otp.equals(serverOtp.getOtp())))
            return false;
        userRepo.changeVerified(emailId); //when otp successfull then verified change to true
        userNameRepo.deleteEntry(emailId);
        return true;
    }

    @Override
    public Boolean isVerified(String emailId) {
        return userRepo.isVerified(emailId);
    }

    @Override
    public Object getUserByDate(String date1, String date2) {
        List<User> users = userRepo.findByDate(date1, date2);
        return users;
    }

    @Override
    public Object getuserByHealthCondition(String healthcondition) {
        List<User> users = userRepo.findByHealthCondition(healthcondition);
        return users;
    }
}
