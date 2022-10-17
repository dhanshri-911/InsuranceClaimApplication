package com.example.insuranceclaimapplication.Repository;

import com.example.insuranceclaimapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    public User findByEmailId(String emailId);
    @Query(value = "Select * from insuranceDB.user where date1= :date1 and date2=:date2",nativeQuery=true)
    List<User> findByDate(String date1, String date2);

    @Query(value="Select * from insuranceDB.user where health_condition = :healthcondition",nativeQuery = true)
    List<User> findByHealthCondition(String healthcondition);

    @Modifying
    @Transactional
    @Query(value = "update insuranceDB.user set verified=true where email_id = :emailId ", nativeQuery = true)
    void changeVerified(String emailId);

    @Query(value = "select verified from insuranceDB.user where email_id = :emailId", nativeQuery = true)
    Boolean isVerified(String emailId);
}


