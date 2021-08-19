package com.example.peopleflowdemo.employeemanagement.endpoint;

import com.example.peopleflowdemo.employeemanagement.api.UnlimitedEmployeeApi;
import com.example.peopleflowdemo.employeemanagement.dto.EmployeePost;
import com.example.peopleflowdemo.employeemanagement.dto.EmployeeResponse;
import com.example.peopleflowdemo.employeemanagement.repository.entity.EmployeeEntity;
import com.example.peopleflowdemo.employeemanagement.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.nio.CharBuffer;
import java.time.ZonedDateTime;

@Slf4j
@RestController
public class UnlimitedEmployeeEndpoint implements UnlimitedEmployeeApi {

    private final EmployeeService employeeService;
    private final PasswordEncoder pssEncoder;

    public UnlimitedEmployeeEndpoint(EmployeeService employeeService, PasswordEncoder pssEncoder) {
        this.employeeService = employeeService;
        this.pssEncoder = pssEncoder;
    }

    /**
     * only a simple logic with a transactional service behind
     *
     * @param body request body for POST method
     * @return response body with employee id
     */
    @Override
    public EmployeeResponse unlimitedEmployeePost(EmployeePost body) {

        log.info(body.toString());

        String pssReadyToPersist = this.pssEncoder.encode(CharBuffer.wrap(body.getPassword()));
        ZonedDateTime openDate = ZonedDateTime.now();

        EmployeeEntity entity = new EmployeeEntity(
                openDate,
                null,
                pssReadyToPersist,
                body.getFirstName(),
                body.getLastName(),
                body.getMobilePhone(),
                body.getEmail());

        entity = this.employeeService.add(entity);

        EmployeeResponse employeeResponse = new EmployeeResponse(
                entity.getEmployeeId(),
                entity.getOpenDate(),
                entity.getCloseDate(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getMobilePhone(),
                entity.getEmail());

        return employeeResponse;
    }

}
