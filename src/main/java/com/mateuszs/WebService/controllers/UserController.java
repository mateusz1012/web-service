package com.mateuszs.WebService.controllers;

import com.mateuszs.WebService.dto.UserDTO;
import com.mateuszs.WebService.model.User;
import com.mateuszs.WebService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/user/first")
    public ResponseEntity<UserDTO> getFirstUser() {

//        UserDTO user = new UserDTO();
//
//        user.setName("Mateusz");
//        user.setLastName("Siekierka");
//
//        return user;

        UserDTO userDTO = userService.getUserById(1L);
        return ResponseEntity.ok(userDTO);

    }

    @GetMapping(path = "/user")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(path = "/user/id/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {

        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping(value = "/user/firstName/{firstName}")
    public ResponseEntity<List<UserDTO>> getUserByFirstName(@PathVariable String firstName) {

        List<UserDTO> userDTO = userService.getUsersByFirstName(firstName);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping(value = "/user/lastName/{lastName}")
    public ResponseEntity<List<UserDTO>> getUserByLastName(@PathVariable String lastName) {

        List<UserDTO> userDTOList = userService.getUsersByLastName(lastName);
        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping(value = "/user/country/{country}")
    public ResponseEntity<List<UserDTO>> getUserByCountry(@PathVariable String country) {

        List<UserDTO> userDTOList = userService.getUsersByCountry(country);
        return ResponseEntity.ok(userDTOList);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id) {
        if (userService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        userService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/user")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PutMapping("/user/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.findById(id)
                .map(user -> {
                    user.setFirstName(userDTO.getFirstName());
                    user.setLastName(userDTO.getLastName());
                    user.setCountry(userDTO.getCountry());
                    user.setPhoneNumber(userDTO.getPhoneNumber());
                    return userService.saveUser(user.dto());
                })
                .orElseGet(() -> {
                    userDTO.setId(id);
                    return userService.saveUser(userDTO);
                });
    }
}
