package com.jobpotal.job.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is empty! please enter a name.")
    private String fullname;
    @NotBlank(message = "please enter email!")
    @Column(unique = true)
    private String email;
    @NotBlank
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    private String phone;
    private LocalDate joinDate;
    @Column(length = 2000)
    private String bio;

    private String resumePath;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educations;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkExperience> workExperiences;


    @ElementCollection
    @CollectionTable(name = "user_portfolio_links", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "portfolio_link")
    private List<String> portfolioLinks;

    private boolean profilePublic = false;


    private boolean notifyJobRecommendations;
    private boolean notifyApplicationUpdates;
    @ElementCollection
    @CollectionTable(name = "user_skills", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "skill")
    private List<String> skills;




}




