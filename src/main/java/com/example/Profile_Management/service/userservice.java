package com.example.Profile_Management.service;

import com.example.Profile_Management.dto.userDTO;
import com.example.Profile_Management.entity.user;
import com.example.Profile_Management.repo.userrepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class userservice {

    @Autowired
    private userrepo userRepository; // Assuming you have a repository for User

    @Autowired
    private ModelMapper modelMapper;

    public String saveUser(userDTO userDTO) {
        // Check if the user already exists by email or another unique field
        if (userRepository.existsById(userDTO.getId())) {
            return "RSP_DUPLICATED"; // Replace with your constant or custom message
        } else {
            userRepository.save(modelMapper.map(userDTO, user.class));
            return "RSP_SUCCESS"; // Replace with your constant or custom message
        }


    }
    public String updateUser(userDTO userDTO) {
        if (userRepository.existsById(userDTO.getId())) {
            // Map UserDTO to User entity and save it
            user user = new user();
            user.setId(userDTO.getId());
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setAddress(userDTO.getAddress());

            userRepository.save(user);

            return "RSP_SUCCESS"; // Response indicating a successful update
        } else {
            return "RSP_NO_DATA_FOUND"; // Response when the user ID doesn't exist
        }
    }
    public userDTO getUser(int id) {
        if (userRepository.existsById(id)) {
            user user = userRepository.findById(id).orElse(null);
            return modelMapper.map(user, userDTO.class);
        } else {
            return null; // Return null if the user does not exist
        }
    }

    public String deleteUser(int id) {
        if (userRepository.existsById(id)) {
            try {
                userRepository.deleteById(id);
                return "User deleted successfully!";
            } catch (Exception e) {
                return "An error occurred while deleting the user: " + e.getMessage();
            }
        } else {
            return "User not found!";
        }
    }
    public userDTO searchUser(int id) {
        if (userRepository.existsById(id)) {
            try {
                user user = userRepository.findById(id).orElse(null);
                return modelMapper.map(user, userDTO.class); // Convert User entity to UserDTO
            } catch (Exception e) {
                throw new RuntimeException("An error occurred while searching for the user: " + e.getMessage());
            }
        } else {
            return null; // Return null if the user is not found
        }
    }









}
