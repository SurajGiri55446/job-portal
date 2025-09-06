package com.jobpotal.job.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "work_experience")
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobTitle;
    private String company;
    private String duration;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}