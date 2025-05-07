package com.nadyagrishina.pro2project.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {
    @NotBlank
    @Size(min = 1, max = 50)
    private String username;
    @NotBlank
    @Size(min = 1, max = 50)
    private String password;
    @Email
    @NotBlank
    @Size(min = 1, max = 100)
    private String email;

    public UserRequestDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
