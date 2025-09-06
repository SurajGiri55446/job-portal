package com.jobpotal.job.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor  @AllArgsConstructor
@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Enter title")
    private String title;
    @NotBlank
    @Column(length = 4000)
    private String description;

    @Column(name = "posted_date")
    private LocalDateTime postedDate;


    @PositiveOrZero
    private double salary;

    @Column(length = 255)
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @NotBlank
    private String company;

    @PrePersist
    public void prePersist() {
        this.postedDate = LocalDateTime.now();
    }

}
