package com.example.Profile_Management.controller;

import com.example.Profile_Management.dto.userDTO;
import com.example.Profile_Management.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class usercontroller {

    @Autowired
    private userservice userService;

    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody userDTO userDTO) {
        try {
            String response = userService.saveUser(userDTO);

            if ("RSP_DUPLICATED".equals(response)) {
                return new ResponseEntity<>("User already exists!", HttpStatus.CONFLICT);
            } else if ("RSP_SUCCESS".equals(response)) {
                return new ResponseEntity<>("User saved successfully!", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Unexpected error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            // Log the exception (optional: use a logger instead of printing to console)
            System.err.println("Error occurred while saving user: " + e.getMessage());
            e.printStackTrace();

            // Return an appropriate response
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody userDTO userDTO) {
        try {
            String response = userService.updateUser(userDTO);

            if ("RSP_SUCCESS".equals(response)) {
                return new ResponseEntity<>("User updated successfully!", HttpStatus.OK);
            } else if ("RSP_NO_DATA_FOUND".equals(response)) {
                return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>("Unexpected error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getUser/{id}")
    public ResponseEntity<Object> getUser(@PathVariable int id) {
        try {
            userDTO userDTO = userService.getUser(id);
            if (userDTO != null) {
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        try {
            String response = userService.deleteUser(id);
            if (response.equals("User deleted successfully!")) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else if (response.equals("User not found!")) {
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/searchUser/{id}")
    public ResponseEntity<Object> searchUser(@PathVariable int id) {
        try {
            userDTO userDTO = userService.searchUser(id);
            if (userDTO != null) {
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}










