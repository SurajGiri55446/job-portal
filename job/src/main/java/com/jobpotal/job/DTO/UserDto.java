package com.jobpotal.job.DTO;

import com.jobpotal.job.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserDto {

    @NotBlank
    private String fullname;

    @NotBlank @Email
    private String email;

    @NotBlank @Size(min = 7)
    private String password;
    @NotNull
    private Role role;
}
