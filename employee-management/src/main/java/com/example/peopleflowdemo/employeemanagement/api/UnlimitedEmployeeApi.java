package com.example.peopleflowdemo.employeemanagement.api;

import com.example.peopleflowdemo.employeemanagement.dto.CommonResponse;
import com.example.peopleflowdemo.employeemanagement.dto.ConstraintResponse;
import com.example.peopleflowdemo.employeemanagement.dto.EmployeePost;
import com.example.peopleflowdemo.employeemanagement.dto.EmployeeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
public interface UnlimitedEmployeeApi {

    @Operation(
            summary = "Adding an employee.", tags = {"Employee"}, description = "An Endpoint for admin to support adding an employee with very basic employee details including (name, contract information, age, you can decide.) With initial state \"ADDED\" which indicates that the employee isn't active yet.",
            parameters = {@Parameter(name = "Accept-Language", in = ParameterIn.HEADER, schema = @Schema(type = "string"))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponse.class))),
                    @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConstraintResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content())
            })
    @PostMapping(value = "/unlimited/employee", consumes = "application/json", produces = "application/json")
    EmployeeResponse unlimitedEmployeePost(
            @RequestBody @Validated EmployeePost bill);

}
