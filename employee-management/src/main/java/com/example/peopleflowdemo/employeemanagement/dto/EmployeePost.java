package com.example.peopleflowdemo.employeemanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(description = "employee POST request body")
@Data
public class EmployeePost {

    @Schema(description = "password")
    private char[] password;

    @Schema(description = "first name")
    private String firstName;

    @Schema(description = "last name")
    private String lastName;

    @Schema(description = "mobile phone")
    @NotNull
    @Pattern(regexp = "\\d{12}")
    private String mobilePhone;

    @Schema(description = "email address")
    @NotEmpty
    @NotNull
    @Email
    private String email;

}
