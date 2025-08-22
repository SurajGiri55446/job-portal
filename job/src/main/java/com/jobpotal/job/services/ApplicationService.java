package com.jobpotal.job.services;

import com.jobpotal.job.DTO.ApplicationDto;
import com.jobpotal.job.Repositories.ApplicationRepository;
import com.jobpotal.job.entity.Application;
import com.jobpotal.job.entity.ApplicationStatus;
import com.jobpotal.job.entity.Job;
import com.jobpotal.job.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService implements  ApplicationServiceImpl{
    @Autowired
    private final ApplicationRepository applicationRepository;
    @Autowired
    private JobService jobService;

    public ApplicationService(ApplicationRepository applicationDto, JobService jobService) {
        this.applicationRepository = applicationDto;
        this.jobService = jobService;
    }

    public Application applyJob(ApplicationDto applicationDto, User user){
        Job job=jobService.findById(applicationDto.getId());
        if(applicationRepository.findByUserAndJob(job,user).isPresent()){
            throw new RuntimeException("Already applied!");
        }
        Application application=new Application();
        application.setJob(job);
        application.setUser(user);
        application.setCoverLetter(applicationDto.getConverLetter());
        application.setStatus(ApplicationStatus.APPLIED);
        return applicationRepository.save(application);
    }

    public List<Application> getApplicationByUser(User user){
        return  applicationRepository.findByUser(user);
    }

    public List<Application> getApplicationByJob(Job job){
        return applicationRepository.findByJob(job);
    }

    public void updateStatus(Long applicationId,ApplicationStatus status){
        Application application=applicationRepository.findById(applicationId).
                orElseThrow(()-> new RuntimeException("application not found"));
        application.setStatus(status);
        applicationRepository.save(application);
    }
}
