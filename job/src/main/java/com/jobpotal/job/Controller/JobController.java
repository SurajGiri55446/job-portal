package com.jobpotal.job.Controller;

import com.jobpotal.job.DTO.JobDto;
import com.jobpotal.job.entity.Job;
import com.jobpotal.job.entity.User;
import com.jobpotal.job.services.JobService;
import com.jobpotal.job.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listJobs(@RequestParam(required = false) String title,
                           @RequestParam(required = false) String location,
                           @RequestParam(name = "minSalary", required = false) Double minSalary,
                           Model model) {

        List<Job> jobs = jobService.searchJob(title, location, minSalary);

        if (jobs == null) {
            jobs = new ArrayList<>();
        }

        model.addAttribute("jobs", jobs);
        return "jobs/list";
    }



    @GetMapping("/post")
    @PreAuthorize("hasRole('EMPLOYER')")
    public String showPostForm(Model model) {
        model.addAttribute("job", new JobDto());
        return "jobs/post";

    }

    @PostMapping("/post")
    @PreAuthorize("hasRole('EMPLOYER')")
    public String postJob(@Valid @ModelAttribute("job") JobDto jobDto, BindingResult result,
                          Authentication authentication){
        if(result.hasErrors()){
            return "jobs/post";
        }
        String email=authentication.getName();
        User user =userService.userEmail(email);
        jobService.postJob(user, jobDto);
        return "redirect:/jobs/my-jobs";
    }


    @GetMapping("/my-jobs")
    @PreAuthorize("hasRole('EMPLOYER')")
    public String myJobs(Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.userEmail(email);
        List<Job> jobs = jobService.getUserByEmployer(user);
        model.addAttribute("jobs", jobs);
        return "jobs/my-jobs";

    }
}
