package com.transation.apptransation.entity;

import lombok.*;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "passport")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "first_name")
    @NotEmpty(message = "User's name cannot be empty.")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "lastName cannot be empty.")
    private String lastName;


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


}
