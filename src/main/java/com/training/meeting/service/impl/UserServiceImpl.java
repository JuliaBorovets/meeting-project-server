package com.training.meeting.service.impl;

import com.training.meeting.domain.user.User;
import com.training.meeting.exception.RegistrationException;
import com.training.meeting.repository.user.UserRepository;
import com.training.meeting.service.UserService;
import com.training.meeting.web.dto.v1.UserDto;
import com.training.meeting.web.mapper.v1.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto saveNewUserDto(UserDto userDto) throws RegistrationException {

        Boolean usernameExists = userRepository.findByUsername(userDto.getUsername()).isPresent();
        Boolean emailExists = userRepository.findByEmail(userDto.getEmail()).isPresent();

        if (usernameExists || emailExists){
            throw new RegistrationException("username or email exists! can not save user !");
        }

        User user = userMapper.userDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

}
