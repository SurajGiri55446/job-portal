package com.jobpotal.job.Controller;

import com.jobpotal.job.DTO.UserProfileDto;
import com.jobpotal.job.Repositories.UserRepository;
import com.jobpotal.job.entity.User;
import com.jobpotal.job.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String viewProfile(Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.userEmail(email);
        model.addAttribute("user", user);
        return "profile/view";
    }

    @GetMapping("/edit")
    public String editProfileForm(Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.userEmail(email);
        if (user == null) {
            return "redirect:/login";
        }
        UserProfileDto profile = new UserProfileDto();
        profile.setFullname(user.getFullname());
        profile.setPhone(user.getPhone());
        profile.setBio(user.getBio());
        profile.setResumePath(user.getResumePath());
        profile.setEducations(user.getEducations());
        profile.setJoinDate(user.getJoinDate());
        profile.setWorkExperiences(user.getWorkExperiences());
        profile.setPortfolioLinks(user.getPortfolioLinks());
        profile.setProfilePublic(user.isProfilePublic());

        profile.setNotifyJobRecommendations(user.isNotifyJobRecommendations());
        profile.setNotifyApplicationUpdates(user.isNotifyApplicationUpdates());
        profile.setSkills(user.getSkills());
        model.addAttribute("profile", profile);
        model.addAttribute("user", user);
        return "profile/edit";
    }

    @PostMapping("/edit")
    public String updateProfile(@Valid @ModelAttribute("profile") UserProfileDto profileDto,
                                BindingResult result,
                                @RequestParam(value = "resume", required = false) MultipartFile resume,
                                @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture,
                                Authentication authentication, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", userService.userEmail(authentication.getName()));
            return "profile/edit";
        }
        try {
            String email = authentication.getName();
            userService.updateProfile(email, profileDto, resume, profilePicture);
            return "redirect:/profile";
        } catch (IOException e) {
            model.addAttribute("error", "Failed to update profile: " + e.getMessage());
            model.addAttribute("user", userService.userEmail(authentication.getName()));
            return "profile/edit";
        }
    }

    @GetMapping("/download-resume-profile/{id}")
    public ResponseEntity<Resource> downloadResume(@PathVariable("id") Long userId) {
        Resource resource = userService.downloadResume(userId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/public")
    @PreAuthorize("hasRole('EMPLOYER')")
    public String viewPublicProfiles(Model model) {
        List<User> publicUsers = userRepository.findByProfilePublicTrue();
        model.addAttribute("users", publicUsers);
        return "profiles/public";
    }

}
