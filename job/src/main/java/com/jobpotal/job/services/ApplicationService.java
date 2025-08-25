package com.jobpotal.job.services;

import com.jobpotal.job.DTO.ApplicationDto;
import com.jobpotal.job.Repositories.ApplicationRepository;
import com.jobpotal.job.entity.Application;
import com.jobpotal.job.entity.ApplicationStatus;
import com.jobpotal.job.entity.Job;
import com.jobpotal.job.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService implements ApplicationServiceImpl {

    private final ApplicationRepository applicationRepository;
    private final JobService jobService;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, JobService jobService) {
        this.applicationRepository = applicationRepository;
        this.jobService = jobService;
    }


    public Application applyJob(ApplicationDto applicationDto, User user, MultipartFile multipartFile) {
        Job job = jobService.findById(applicationDto.getJobId());

        Optional<Application> existingApplication = applicationRepository.findByUserAndJob(user, job);
        if (existingApplication.isPresent()) {
            throw new RuntimeException("You have already applied to this job!");
        }

        Application application = new Application();
        application.setJob(job);
        application.setUser(user);
        application.setCoverLetter(applicationDto.getCoverLetter());
        application.setStatus(ApplicationStatus.APPLIED);

        if (!multipartFile.isEmpty()) {
            try {

                Path uploadDir = Paths.get("uploads/resumes");
                Files.createDirectories(uploadDir);

                String filename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
                Path filePath = uploadDir.resolve(filename);

                Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);


                application.setResumePath(filePath.toString());

            } catch (IOException e) {
                throw new RuntimeException("couldn't store file. Error: " + e.getMessage());
            }
        }

        return applicationRepository.save(application);
    }



    public List<Application> getApplicationByUser(User user) {
        return applicationRepository.findByUser(user);
    }


    public List<Application> getApplicationByJob(Job job) {
        return applicationRepository.findByJob(job);
    }


    public void updateStatus(Long applicationId, ApplicationStatus status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + applicationId));
        application.setStatus(status);
        applicationRepository.save(application);
    }

    public Resource downloadResume(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + applicationId));

        try {
            Path path = Paths.get(application.getResumePath());
            return new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error while reading file: " + e.getMessage());
        }
    }




}