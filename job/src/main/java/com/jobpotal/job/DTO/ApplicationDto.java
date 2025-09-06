package com.jobpotal.job.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApplicationDto {



    private Long jobId;
    @NotBlank(message = "cover letter is required")

    private String coverLetter;

    private String resumePath;

}
