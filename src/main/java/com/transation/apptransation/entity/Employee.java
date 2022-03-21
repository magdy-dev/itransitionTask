package com.transation.apptransation.entity;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;



@Entity
@ToString
@Table(name = "employees")
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty(message = "email cannot be empty.")
    @Email(message = "emil not correcte")
    private String email;

    @Column(name = "university_code", unique = true, length = 10)
    @NotEmpty(message = "universityCode cannot be empty.")
    private String universityCode;

    @Column(name = "message")
    private String message;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    public Employee(long id, String email, String universityCode, String message, Passport passport) {
        this.id = id;
        this.email = email;
        this.universityCode = universityCode;
        this.message = message;
        this.passport = passport;
    }

    public Employee() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversityCode() {
        return universityCode;
    }

    public void setUniversityCode(String universityCode) {
        this.universityCode = universityCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }
}