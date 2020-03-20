package com.mateuszs.WebService.services;

import com.mateuszs.WebService.dto.UserDTO;
import com.mateuszs.WebService.jpa.services.UserRepository;
import com.mateuszs.WebService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().dto();
        }
        return null;
    }

    public List<UserDTO> getUsersByFirstName(String firstName) {
        List<User> users = userRepository.findAllByFirstName(firstName);
        List<UserDTO> userDTO = new ArrayList<>();
        for (User user : users) {
            userDTO.add(user.dto());
        }
        return userDTO;
    }

    @Transactional //musi być
    public UserDTO deleteUserById(Long id) {
        Optional<User> user = userRepository.deleteUserById(id);
        if (user.isPresent()) {
            return user.get().dto();
        }
        return null;
    }


    public UserDTO save(UserDTO userDTO) {
        return userDTO;
    }
}