package com.jobpotal.job.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String degree;
    private String institution;
    private String year;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}