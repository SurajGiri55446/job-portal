package com.jobpotal.job.Controller;

import com.jobpotal.job.DTO.UserDto;
import com.jobpotal.job.entity.User;
import com.jobpotal.job.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;



    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistration(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserDto userDto,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        try {
            User savedUser = userService.userRegister(userDto);
            return "redirect:/login?success";
        } catch (RuntimeException e) {
            result.rejectValue("email", null, e.getMessage());
            return "register";
        }
    }

    @GetMapping("/nav")
    public String nav() {
        return "Navheader";
    }
}
