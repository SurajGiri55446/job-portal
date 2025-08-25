package com.jobpotal.job.Repositories;

import com.jobpotal.job.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface
UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM user WHERE fullname = :fullname", nativeQuery = true)
    List<User> getUserByFullname(@Param("fullname") String fullname);

}
