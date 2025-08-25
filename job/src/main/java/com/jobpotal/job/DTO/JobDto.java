package com.jobpotal.job.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class JobDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @PositiveOrZero
    private double salary;
    @NotBlank
    private String location;
    @NotBlank
    private String company;
}