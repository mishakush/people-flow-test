package com.example.peopleflowdemo.employeemanagement.service;

import com.example.peopleflowdemo.employeemanagement.repository.EmployeeRepository;
import com.example.peopleflowdemo.employeemanagement.repository.entity.EmployeeEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BaseEmployeeService implements EmployeeService {

    private final EmployeeRepository repository;

    public BaseEmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public EmployeeEntity add(EmployeeEntity entity) {

        entity = this.repository.save(entity);

        return entity;
    }
}
