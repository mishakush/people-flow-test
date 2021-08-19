package com.example.peopleflowdemo.employeemanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "employee response body")
public class EmployeeResponse {

    @Schema(description = "employee unique identifier")
    private Long employeeId;

    @Schema(description = "employee adding date")
    private ZonedDateTime openDate;

    @Schema(description = "employee deactivation date")
    private ZonedDateTime closeDate;

    @Schema(description = "first name")
    private String firstName;

    @Schema(description = "last name")
    private String lastName;

    @Schema(description = "mobile phone")
    private String mobilePhone;

    @Schema(description = "email address")
    private String email;
}

