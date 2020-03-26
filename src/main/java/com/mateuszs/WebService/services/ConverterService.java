package com.mateuszs.WebService.services;

import com.mateuszs.WebService.dto.UserDTO;
import com.mateuszs.WebService.model.User;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {

    //konwerter User na UserDTO
    public UserDTO convertUserToUserDTO(User user) {

        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .country(user.getCountry())
                .phoneNumber(user.getPhoneNumber()).build();
    }

    //konwerterUserDTO na User
    public User convertUserDTOToUser(UserDTO userDTO) {

        return User.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .country(userDTO.getCountry())
                .phoneNumber(userDTO.getPhoneNumber()).build();
    }
}
