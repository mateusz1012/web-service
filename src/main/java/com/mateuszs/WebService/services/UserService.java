package com.mateuszs.WebService.services;

import com.mateuszs.WebService.dto.UserDTO;
import com.mateuszs.WebService.jpa.services.UserRepository;
import com.mateuszs.WebService.model.User;
import lombok.RequiredArgsConstructor;
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

    @Autowired
    private ConverterService converterService; //wstrzykiwanie przez pole

    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().dto();
        }
        return null;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<UserDTO> getUsersByFirstName(String firstName) {
        List<User> users = userRepository.findAllByFirstName(firstName);
        List<UserDTO> userDTO = new ArrayList<>();
        for (User user : users) {
            userDTO.add(user.dto());
        }
        return userDTO;
    }

    public List<UserDTO> getUsersByLastName(String lastName) {
        List<User> users = userRepository.findAllByLastName(lastName);
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : users) {
            userDTOList.add(user.dto());
        }
        return userDTOList;
    }

    public List<UserDTO> getUsersByCountry(String country) {
        List<User> users = userRepository.findAllByCountry(country);
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : users) {
            userDTOList.add(user.dto());
        }
        return userDTOList;
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
        User user = converterService.convertUserDTOToUser(userDTO);
        User userToReturn = userRepository.save(user);
        return userToReturn.dto();
    }
}
