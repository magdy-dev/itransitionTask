package com.transation.apptransation.entity;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;



@Entity
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "visa")
    private boolean visa;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;


}