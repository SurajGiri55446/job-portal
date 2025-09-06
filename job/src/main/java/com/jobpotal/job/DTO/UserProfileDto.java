package com.jobpotal.job.DTO;

import com.jobpotal.job.entity.Education;
import com.jobpotal.job.entity.WorkExperience;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserProfileDto {
    private String fullname;
    private String email;
    private String phone;
    private String bio;

    private String resumePath;
    private LocalDate joinDate;
    private List<Education> educations;
    private List<WorkExperience> workExperiences;

    private List<String> portfolioLinks;

    private boolean profilePublic;

    private boolean notifyJobRecommendations;
    private boolean notifyApplicationUpdates;

   private List<String> skills;

}
