package com.jobpotal.job.Repositories;

import com.jobpotal.job.entity.Application;
import com.jobpotal.job.entity.Job;
import com.jobpotal.job.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
    Optional<Application> findByUserAndJob(Job job,User user);
    List<Application> findByJob(Job job);
    List<Application> findByUser(User user);
}
