package com.jobpotal.job.Controller;


import com.jobpotal.job.Repositories.UserRepository;

import com.jobpotal.job.entity.User;
import com.jobpotal.job.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    private static final String ROLE_EMPLOYER = "ROLE_EMPLOYER";
    private static final String ROLE_APPLICANT = "ROLE_APPLICANT";

    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication ) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        // In your dashboard method
//        String email = authentication.getName();
//        User user = (User) userService.getUserName(email);
//        String fullName = user.getFullname();
//        model.addAttribute("fullname", fullName);




        String email = authentication.getName();
        String username = email.substring(0, email.indexOf("@"));
        model.addAttribute("userEmail", username);


        // Check roles
        boolean isEmployer = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(ROLE_EMPLOYER));
        boolean isApplicant = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(ROLE_APPLICANT));

        model.addAttribute("isEmployer", isEmployer);
        model.addAttribute("isApplicant", isApplicant);

        return "dashboard";
    }

      public String userName(String name,Model model){

        model.addAttribute("userName",userService.getUserName(name));
       return "Navheader";
      }

}
