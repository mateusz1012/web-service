package com.mateuszs.WebService.model;

import com.mateuszs.WebService.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "users")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "country")
    private String country;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    public UserDTO dto() {

        return UserDTO.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .country(this.country)
                .phoneNumber(this.phoneNumber).build();
    }
}
