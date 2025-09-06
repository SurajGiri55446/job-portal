package com.jobpotal.job.services;

import com.jobpotal.job.DTO.UserDto;
import com.jobpotal.job.DTO.UserProfileDto;
import com.jobpotal.job.Repositories.UserRepository;

import com.jobpotal.job.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${upload.resume.dir:uploads/resumes}")
    private String resumeUploadDir;



    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User userRegister(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        User user = new User();
        user.setFullname(userDto.getFullname());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // encode password
        user.setRole(userDto.getRole());
        user.setBio(userDto.getBio());
        user.setPhone(userDto.getPhone());
//        user.setProfilePicturePath(userDto.getProfilePicturePath());
        return userRepository.save(user);
    }

    public User userEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<User> getUserName(String name){
        return userRepository.getUserByFullname(name);
    }


    public User updateProfile(String email, UserProfileDto profileDto,
                              MultipartFile resume, MultipartFile profilePicture) throws IOException {

        User user = userEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        user.setFullname(profileDto.getFullname());
        user.setPhone(profileDto.getPhone());
        user.setBio(profileDto.getBio());
        user.setJoinDate(LocalDate.now());
        user.setPortfolioLinks(profileDto.getPortfolioLinks());
        user.setProfilePublic(profileDto.isProfilePublic());
        user.setSkills(profileDto.getSkills());

        user.setNotifyJobRecommendations(profileDto.isNotifyJobRecommendations());
        user.setNotifyApplicationUpdates(profileDto.isNotifyApplicationUpdates());

        // --- Handle Resume Upload ---
        if (resume != null && !resume.isEmpty()) {
            Files.createDirectories(Paths.get(resumeUploadDir));

            // Delete old resume if exists
            if (user.getResumePath() != null) {
                Path oldResumePath = Paths.get(resumeUploadDir).resolve(user.getResumePath());
                Files.deleteIfExists(oldResumePath);
            }

            String filename = System.currentTimeMillis() + "_" + resume.getOriginalFilename();
            Path resumePath = Paths.get(resumeUploadDir, filename);
            Files.copy(resume.getInputStream(), resumePath, StandardCopyOption.REPLACE_EXISTING);

            // ✅ Save only the file name
            user.setResumePath(filename);
        }


        if (profileDto.getEducations() != null) {
            user.getEducations().clear();
            profileDto.getEducations().forEach(edu -> {
                edu.setUser(user);
                user.getEducations().add(edu);
            });
        }
        if (profileDto.getWorkExperiences() != null) {
            user.getWorkExperiences().clear();
            profileDto.getWorkExperiences().forEach(exp -> {
                exp.setUser(user);
                user.getWorkExperiences().add(exp);
            });
        }

        return userRepository.save(user);
    }

    public Resource downloadResume(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        try {
            Path path = Paths.get(resumeUploadDir).resolve(user.getResumePath());

            if (!Files.exists(path)) {
                throw new RuntimeException("Resume file not found at path: " + path);
            }

            return new UrlResource(path.toUri());

        } catch (MalformedURLException e) {
            throw new RuntimeException("Error while reading file: " + e.getMessage());
        }
    }




}
