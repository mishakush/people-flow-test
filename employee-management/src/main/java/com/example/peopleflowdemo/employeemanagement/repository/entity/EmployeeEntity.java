package com.example.peopleflowdemo.employeemanagement.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
@Entity
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "open_date")
    private ZonedDateTime openDate;

    @Column(name = "close_date")
    private ZonedDateTime closeDate;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "email")
    private String email;


    public EmployeeEntity(ZonedDateTime openDate, ZonedDateTime closeDate, String password, String firstName, String lastName, String mobilePhone, String email) {
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.email = email;
    }
}
