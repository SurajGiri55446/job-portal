package com.jobpotal.job.services;

import com.jobpotal.job.DTO.JobDto;
import com.jobpotal.job.Repositories.JobRepository;
import com.jobpotal.job.Repositories.UserRepository;
import com.jobpotal.job.entity.Job;
import com.jobpotal.job.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private  final JobRepository jobRepository;

    public JobService(UserRepository userRepository, JobRepository jobRepository) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    public Job postJob(User user , JobDto jobDto){
        Job job=new Job();
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setSalary(jobDto.getSalary());
        job.setLocation(jobDto.getLocation());
        job.setUser(user);
        return jobRepository.save(job);
    }

    public List<Job> searchJob(String title, String locations, Double salary) {
        return jobRepository.searchJobs(title, locations, salary);
    }


    public List<Job> getUserByEmployer(User user){
        return jobRepository.findByUser(user);
    }

    public Job findById(Long id){
        return jobRepository.findById(id).orElseThrow(()->new RuntimeException("job not found"));
    }
}
