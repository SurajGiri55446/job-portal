package com.jobpotal.job.services;

import com.jobpotal.job.DTO.ApplicationDto;
import com.jobpotal.job.entity.Application;
import com.jobpotal.job.entity.ApplicationStatus;
import com.jobpotal.job.entity.Job;
import com.jobpotal.job.entity.User;

import java.util.List;

public interface ApplicationServiceImpl {
    Application applyJob(ApplicationDto applicationDto, User user);
    List<Application> getApplicationByUser(User user);
    List<Application> getApplicationByJob(Job job);
    void updateStatus(Long applicationId, ApplicationStatus status);
}
