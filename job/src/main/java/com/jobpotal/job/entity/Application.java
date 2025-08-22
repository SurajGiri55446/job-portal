package com.jobpotal.job.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "Application"
    ,uniqueConstraints = @UniqueConstraint(columnNames = {"job_id","applicant_id"}))
public class Application {
    @Id
    private  Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "job_id")
    private Job job;
    @ManyToOne(optional = false) @JoinColumn(name = "user_id")
    private  User  user;

    private LocalTime time=LocalTime.now();

    private ApplicationStatus status=ApplicationStatus.APPLIED;
    @Column(length = 400)
    private String coverLetter;

}
