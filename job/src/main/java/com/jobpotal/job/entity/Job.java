package com.jobpotal.job.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor @Data @AllArgsConstructor
@Entity @Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Enter title")
    private String title;
    @NotBlank
    @Column(length = 3000)
    private String description;

    private LocalTime time=LocalTime.now();
    @PositiveOrZero
    private double salary;
    @Column(length = 255)
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @NotBlank
    private String company;
}
