package com.example.peopleflowdemo.employeemanagement.repository;

import com.example.peopleflowdemo.employeemanagement.repository.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long > {
}
