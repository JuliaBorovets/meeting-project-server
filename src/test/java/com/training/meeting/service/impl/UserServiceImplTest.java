package com.training.meeting.service.impl;

import com.training.meeting.domain.user.Role;
import com.training.meeting.domain.user.User;
import com.training.meeting.exception.RegistrationException;
import com.training.meeting.exception.RoleCanNotFindException;
import com.training.meeting.exception.UserCanNotFindException;
import com.training.meeting.repository.user.UserRepository;
import com.training.meeting.service.RoleService;
import com.training.meeting.web.dto.v1.UserDto;
import com.training.meeting.web.mapper.v1.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    RoleService roleService;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl userService;

    UserDto userDto;

    User user;

    final Long ID = 1L;
    final String PASSWORD = "password";
    final String USERNAME = "username";
    final String EMAIL = "email@gmail.com";
    final Long ROLE_ID = 4L;

    @BeforeEach
    void setUp() {
        userDto = UserDto.builder()
                .id(ID)
                .username(USERNAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        user =  User.builder()
                .id(ID)
                .username(USERNAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();
    }

    @Test
    void shouldSaveNewUserDto() throws RegistrationException, RoleCanNotFindException {

        when(userMapper.userDtoToUser(any(UserDto.class))).thenReturn(user);
        when(userMapper.userToUserDto(user)).thenReturn(userDto);
        when(passwordEncoder.encode(anyString())).thenReturn(PASSWORD);
        when(roleService.findRoleByName(anyString())).thenReturn(Role.builder().id(ROLE_ID).build());

        UserDto result = userService.saveNewUserDto(userDto);

        assertEquals( PASSWORD, result.getPassword());

        verify(userRepository).save(any());
        verify(passwordEncoder).encode(anyString());
        verify(userMapper).userToUserDto(any(User.class));
        verify(userMapper).userDtoToUser(any(UserDto.class));
    }

    @Test
    void shouldThrowRegistrationExceptionUsernameAlreadyExists() throws RoleCanNotFindException {

        when(userMapper.userDtoToUser(any(UserDto.class))).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn(PASSWORD);
        when(roleService.findRoleByName(anyString())).thenReturn(Role.builder().id(ROLE_ID).build());

        doThrow(DataIntegrityViolationException.class).when(userRepository).save(any(User.class));

        assertThrows(RegistrationException.class,
                () -> {
                    userService.saveNewUserDto(userDto);
                });

        verify(userMapper).userDtoToUser(any(UserDto.class));
        verify(roleService).findRoleByName(anyString());
        verify(passwordEncoder).encode(anyString());
        verify(userMapper, times(0)).userToUserDto(any(User.class));
    }

    @Test
    void shouldUpdateUser() throws  UserCanNotFindException {
        when(passwordEncoder.encode(any())).thenReturn(PASSWORD);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userMapper.userToUserDto(any(User.class))).thenReturn(userDto);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDto result = userService.updateUserDto(userDto);

        verify(userMapper).userToUserDto(any(User.class));
        verify(passwordEncoder).encode(anyString());
        verify(userRepository).findById(anyLong());
        verify(userRepository).save(any(User.class));

    }

    @Test
    void shouldThrowUserNotFindExceptionAndNotCreateUser() throws  UserCanNotFindException {

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserCanNotFindException.class,
                () -> {
                    userService.updateUserDto(userDto);
                });

        verify(userMapper, times(0)).userToUserDto(any(User.class));
        verify(passwordEncoder, times(0)).encode(anyString());
        verify(userRepository).findById(anyLong());
        verify(userRepository, times(0)).save(any(User.class));

    }
}
