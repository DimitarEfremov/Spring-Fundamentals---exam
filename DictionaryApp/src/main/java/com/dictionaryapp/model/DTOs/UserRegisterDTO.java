package com.dictionaryapp.model.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDTO {

    @NotEmpty (message = "This field can not be empty")
    @Size(min = 2, max = 20, message = "Field must be between 2 and 20 characters")
    private String username;

    @Email (message = "Email is not valid")
    @NotEmpty (message = "This field can not be empty")
    private String email;

    @NotEmpty (message = "This field can not be empty")
    private String password;

    @NotEmpty (message = "This field can not be empty")
    private String confirmPassword;

}
