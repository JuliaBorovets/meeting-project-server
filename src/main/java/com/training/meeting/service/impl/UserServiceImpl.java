package com.training.meeting.service.impl;

import com.training.meeting.domain.user.Role;
import com.training.meeting.domain.user.User;
import com.training.meeting.exception.RegistrationException;
import com.training.meeting.exception.RoleCanNotFindException;
import com.training.meeting.exception.UserCanNotFindException;
import com.training.meeting.repository.user.UserRepository;
import com.training.meeting.service.RoleService;
import com.training.meeting.service.UserService;
import com.training.meeting.web.dto.v1.UserDto;
import com.training.meeting.web.mapper.v1.UserMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public UserDto saveNewUserDto(UserDto userDto) throws RegistrationException, RoleCanNotFindException {

        User user = userMapper.userDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role defaultRole = roleService.findRoleByName("USER");
        user.getRoles().add(defaultRole);

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RegistrationException("username or email exists! can not save user !");
        }

        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUserDto(UserDto userDto) throws UserCanNotFindException {

        User user = findUserById(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userMapper.userToUserDto(userRepository.save(user));
    }

    private User findUserById(Long id) throws UserCanNotFindException {
        return userRepository.findById(id)
                .orElseThrow(UserCanNotFindException::new);
    }
}
