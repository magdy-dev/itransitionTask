package com.transation.apptransation.entity;



import lombok.Builder;
import lombok.Data;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Data
@Entity
@Builder
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    @NotEmpty(message = "User's name cannot be empty.")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "lastName cannot be empty.")
    private String lastName;

    @Column(name = "email",nullable = false, unique = true)
    @NotEmpty(message = "email cannot be empty.")
    private String email;

    @Column(name = "passport_number", unique = true)
    @NotEmpty(message = "passportNumber cannot be empty.")
    private String passportNumber;
    @Column(name = "date")
    @NotEmpty(message = "date cannot be empty.")
    private String date;

    @Column(name = "country")
    @NotEmpty(message = "country cannot be empty.")
    private String country;

    @Column(name = "passport_Expired")
    @NotEmpty(message = "passportExpired cannot be empty.")
    private String passportExpired;

    @Column(name = "birthday")
    @NotEmpty(message = "birthday cannot be empty.")
    private String birthday;

    @Column(name = "university_code", unique = true, length = 10)
    @NotEmpty(message = "universityCode cannot be empty.")
    private String universityCode;

    @Column(name = "message" )
    private String message;

    public Employee() {

    }

    public Employee(long id, String firstName, String lastName, String email, String passportNumber, String date, String country, String passportExpired, String birthday, String universityCode, String message) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passportNumber = passportNumber;
        this.date = date;
        this.country = country;
        this.passportExpired = passportExpired;
        this.birthday = birthday;
        this.universityCode = universityCode;
        this.message = message;
    }
}