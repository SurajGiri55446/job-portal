package com.jobpotal.job.services;

import com.jobpotal.job.DTO.JobDto;
import com.jobpotal.job.Repositories.ApplicationRepository;
import com.jobpotal.job.Repositories.JobRepository;
import com.jobpotal.job.Repositories.UserRepository;
import com.jobpotal.job.entity.Job;
import com.jobpotal.job.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private  final JobRepository jobRepository;


    private final ApplicationRepository applicationRepository;

    public JobService(UserRepository userRepository, JobRepository jobRepository, ApplicationRepository applicationRepository) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
    }

    public Job postJob(User user , JobDto jobDto){
        Job job=new Job();
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setSalary(jobDto.getSalary());
        job.setCompany(jobDto.getCompany());
        job.setLocation(jobDto.getLocation());
        job.setUser(user);
        return jobRepository.save(job);
    }

    public List<Job> searchJob(String title, String location, Double salary) {
        String t = (title == null || title.isBlank()) ? null : title.toLowerCase();
        String loc = (location == null || location.isBlank()) ? null : location.toLowerCase();
        return jobRepository.searchJobs(t, loc, salary);
    }



    public List<Job> getUserByEmployer(User user){
        return jobRepository.findByUser(user);
    }

    public Job findById(Long id){
        return jobRepository.findById(id).orElseThrow(()->new RuntimeException("job not found"));
    }

    @Transactional
    public void deleteJobById(Long jobId) {
        applicationRepository.deleteByJobId(jobId);
        jobRepository.deleteById(jobId);
    }

    @Transactional
    public Job updateJob(Long jobId, JobDto jobDto) {
        Optional<Job> optionalJob = jobRepository.findById(jobId);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.setTitle(jobDto.getTitle());
            job.setCompany(jobDto.getCompany());
            job.setDescription(jobDto.getDescription());
            job.setSalary(jobDto.getSalary());
            job.setLocation(jobDto.getLocation());
            return jobRepository.save(job);
        }
        return null;
    }






}
