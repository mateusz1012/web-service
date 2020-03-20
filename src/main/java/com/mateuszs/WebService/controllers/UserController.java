package com.mateuszs.WebService.controllers;

import com.mateuszs.WebService.dto.UserDTO;
import com.mateuszs.WebService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
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

    @DeleteMapping(path = "/user/delete/{id}")
    public void deleteUserById(@PathVariable Long id) {

        userService.deleteUserById(id);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.save(userDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
}
