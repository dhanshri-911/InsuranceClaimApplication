package com.example.insuranceclaimapplication.controller;

import com.example.insuranceclaimapplication.dto.LoginDTO;
import com.example.insuranceclaimapplication.dto.ResponseDTO;
import com.example.insuranceclaimapplication.dto.UserDTO;
import com.example.insuranceclaimapplication.dto.UserNameOtpDTO;
import com.example.insuranceclaimapplication.model.User;
import com.example.insuranceclaimapplication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    IUserService iUserService;

    @Autowired
    PasswordEncoder passwordEncoder;

    final static String SUCCESS = "Entered Otp is valid, and Login was successful.";
    final static String FAIL = "Entered OTP was not valid! , Login failed!, please try again";

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO userDTO){
        ResponseDTO responseDTO = new ResponseDTO("User registration Successful" , iUserService.registerUser(userDTO));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> showAll(){
        ResponseDTO responseDTO = new ResponseDTO("All User Records" , iUserService.showAll());
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getid/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable Long id){
        ResponseDTO responseDTO = new ResponseDTO("Found User By Id "+id , iUserService.findById(id));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable Long id,@RequestBody UserDTO userDTO){
        ResponseDTO responseDTO = new ResponseDTO("Found User By Id "+id , iUserService.update(id, userDTO));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<ResponseDTO> remove(@PathVariable Long id){
        ResponseDTO responseDTO = new ResponseDTO("Record Successfully"+id , iUserService.remove(id));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        loginDTO.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        User user =null;
         user = iUserService.login(loginDTO);
         ResponseDTO responseDTO = new ResponseDTO("User Login Successfully",user);
         return  new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);

    }

    @PostMapping({"/verifyotp"})
    public String verifyOtp(@Valid @RequestBody UserNameOtpDTO userNameOtpDTO) {
        String username = userNameOtpDTO.getEmailId();
        String otp = userNameOtpDTO.getOtp();
        Boolean isVerifyOtp = iUserService.verifyOtp(username, otp);
        if (!isVerifyOtp)
            return FAIL;
        return SUCCESS;
    }


    @GetMapping("/getuserByDate")
    public ResponseEntity<ResponseDTO> getUserByDate(@RequestParam String date1, @RequestParam String date2){
        ResponseDTO responseDTO = new ResponseDTO("All User Records" , iUserService.getUserByDate(date1, date2));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getuserByHealthCondition/{healthcondition}")
    public ResponseEntity<ResponseDTO> getuserByHealthCondition(@PathVariable String healthcondition){
        ResponseDTO responseDTO = new ResponseDTO("All User Records" , iUserService.getuserByHealthCondition(healthcondition));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
