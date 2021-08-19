package com.example.peopleflowdemo.employeemanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(description = "4xx/5xx response body")
@Data
@Builder
public class CommonResponse {


    @Schema(description = "internal code")
    private String code;
    @Schema(description = "message for developer")
    private String devMessage;
    @Schema(description = "multilingual user message for customer")
    private String userMessage;


}
