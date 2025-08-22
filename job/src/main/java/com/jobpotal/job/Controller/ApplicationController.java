package com.jobpotal.job.Controller;

import com.jobpotal.job.DTO.ApplicationDto;
import com.jobpotal.job.entity.Application;
import com.jobpotal.job.entity.ApplicationStatus;
import com.jobpotal.job.entity.Job;
import com.jobpotal.job.entity.User;
import com.jobpotal.job.services.ApplicationService;
import com.jobpotal.job.services.JobService;
import com.jobpotal.job.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final JobService jobService;
    private final UserService userService;

    public ApplicationController(ApplicationService applicationService,
                                 JobService jobService,
                                 UserService userService) {
        this.applicationService = applicationService;
        this.jobService = jobService;
        this.userService = userService;
    }

    @GetMapping("/apply/{id}")
    @PreAuthorize("hasRole('APPLICANT')")
    public String showApplyForm(@PathVariable Long id, Model model) {
        model.addAttribute("application", new ApplicationDto());
        model.addAttribute("jobId", id);
        return "applications/apply";
    }

    @PostMapping("/apply")
    @PreAuthorize("hasRole('APPLICANT')")
    public String apply(@ModelAttribute("application") ApplicationDto applicationDto,
                        Authentication authentication) {
        String email = authentication.getName();
        User user = userService.userEmail(email);
        applicationService.applyJob(applicationDto, user);
        return "redirect:/applications/my-applications";
    }

    @GetMapping("/my-applications")
    @PreAuthorize("hasRole('APPLICANT')")
    public String myApplication(Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.userEmail(email);
        List<Application> applications = applicationService.getApplicationByUser(user);
        model.addAttribute("applications", applications);
        return "applications/my-applications";
    }

    @GetMapping("/job/{id}")
    @PreAuthorize("hasRole('EMPLOYER')")
    public String viewJobApplication(@PathVariable Long id, Model model) {
        Job job = jobService.findById(id);
        List<Application> applications = applicationService.getApplicationByJob(job);
        model.addAttribute("applications", applications);
        model.addAttribute("job", job);
        return "applications/job-applications";
    }

    @PostMapping("/update-status/{appId}")
    @PreAuthorize("hasRole('EMPLOYER')")
    public String updateStatus(@PathVariable Long appId,
                               @RequestParam ApplicationStatus status,
                               @RequestParam Long jobId) {
        applicationService.updateStatus(appId, status);
        return "redirect:/applications/job/" + jobId;
    }
}
