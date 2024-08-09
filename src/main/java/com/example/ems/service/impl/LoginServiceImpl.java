package com.example.ems.service.impl;

import com.example.ems.model.User;
import com.example.ems.model.UserDto;
import com.example.ems.model.Role;
import com.example.ems.repository.UserRepository;
import com.example.ems.service.LoginService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveNewUser(UserDto userDto) {
        User user = mapDtoToEntity(userDto);
        this.userRepository.save(user);
    }

    private User mapDtoToEntity(UserDto userDto){
        User user  = new User();
        user.setUsername(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encryptPassword(userDto.getPassword()));
        user.setCreatedOn(LocalDateTime.now());
        user.setRole(Role.USER);
        return user;
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }



}
