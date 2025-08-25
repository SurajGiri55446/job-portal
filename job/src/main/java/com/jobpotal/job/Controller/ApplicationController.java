package com.jobpotal.job.Controller;

import com.jobpotal.job.DTO.ApplicationDto;
import com.jobpotal.job.entity.Application;
import com.jobpotal.job.entity.ApplicationStatus;
import com.jobpotal.job.entity.Job;
import com.jobpotal.job.entity.User;
import com.jobpotal.job.services.ApplicationService;
import com.jobpotal.job.services.JobService;
import com.jobpotal.job.services.UserService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setJobId(id);
        model.addAttribute("application", applicationDto);
        return "applications/apply";
    }

    @PostMapping("/apply")
    @PreAuthorize("hasRole('APPLICANT')")
    public String apply(@ModelAttribute("application") ApplicationDto dto, Authentication authentication,
                        @RequestParam("resumeFile") MultipartFile resumeFile) {
        String email = authentication.getName();
        User user = userService.userEmail(email);
        applicationService.applyJob(dto, user,resumeFile);
        return "redirect:/applications/my-applications";
    }

    @GetMapping("/my-applications")
    @PreAuthorize("hasRole('APPLICANT')")
    public String myApplications(Model model, Authentication authentication) {
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

    @GetMapping("/download-resume/{appId}")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<Resource> downloadResume(@PathVariable Long appId) {
        Resource resource = (Resource) applicationService.downloadResume(appId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}