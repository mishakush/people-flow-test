package com.example.peopleflowdemo.employeemanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Schema(description = "detailed validation response body")
@AllArgsConstructor
@Data
public class ConstraintResponse {

    @Schema(description = "validation messages per attribute")
    private Map<String, String> validationMessage;

}
