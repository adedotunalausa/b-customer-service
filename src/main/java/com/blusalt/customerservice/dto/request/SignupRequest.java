package com.blusalt.customerservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class SignupRequest {
    @NotBlank(message = "username cannot be empty")
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank(message = "firstname cannot be empty")
    private String firstname;

    @NotBlank(message = "lastname cannot be empty")
    private String lastname;

    @NotBlank(message = "gender cannot be empty")
    private String gender;

    @NotBlank(message = "email cannot be empty")
    @Size(max = 50)
    @Email(message = "must be email")
    private String email;

    @NotBlank(message = "password cannot be empty")
    @Size(min = 6, max = 24)
    private String password;

}
