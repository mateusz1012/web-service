package com.mateuszs.WebService.model;

import com.mateuszs.WebService.dto.UserDTO;

import javax.persistence.*;

@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Id")
    private Long id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Country")
    private String country;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    public UserDTO dto() {

        return UserDTO.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .id(this.id)
                .country(this.country)
                .phoneNumber(this.phoneNumber).build();
    }
}
