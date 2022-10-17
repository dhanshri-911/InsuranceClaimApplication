package com.example.insuranceclaimapplication.Repository;

import com.example.insuranceclaimapplication.model.User;
import com.example.insuranceclaimapplication.model.UserNameOtpData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserNameRepo extends JpaRepository<UserNameOtpData, String> {
    public UserNameOtpData findByEmailId(String emailId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_name_otp_data WHERE email_id= :emailId", nativeQuery = true)
    void deleteEntry(String emailId);


}
