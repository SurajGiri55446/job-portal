package com.jobpotal.job.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application", // Use lowercase for table name
        uniqueConstraints = @UniqueConstraint(columnNames = {"job_id", "user_id"})) // Fixed column names
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Added auto-generation
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalTime appliedTime=LocalTime.now();

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    @Column(length = 1000)
    private String coverLetter;

    private String resumePath;
}