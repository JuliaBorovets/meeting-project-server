package com.training.meeting.service.impl;

import com.training.meeting.domain.user.User;
import com.training.meeting.exception.RegistrationException;
import com.training.meeting.repository.user.UserRepository;
import com.training.meeting.web.dto.v1.UserDto;
import com.training.meeting.web.mapper.v1.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl userService;

    UserDto userDto;

    User user;

    final Long ID = 1L;
    final String PASSWORD = "password";
    final String USERNAME = "username";
    final String EMAIL = "email@gmail.com";

    @BeforeEach
    void setUp() {
        userDto = UserDto.builder()
                .username(USERNAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        user =  User.builder()
                .username(USERNAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();
    }

    @Test
    void shouldSaveNewUserDto() throws RegistrationException {

        when(userMapper.userDtoToUser(any(UserDto.class))).thenReturn(user);
        when(userMapper.userToUserDto(user)).thenReturn(userDto);
        when(passwordEncoder.encode(anyString())).thenReturn(PASSWORD);
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        UserDto result = userService.saveNewUserDto(userDto);

        assertEquals( PASSWORD, result.getPassword());

        verify(userRepository).save(any());
        verify(passwordEncoder).encode(anyString());
        verify(userMapper).userToUserDto(any(User.class));
        verify(userMapper).userDtoToUser(any(UserDto.class));
        verify(userRepository).findByUsername(anyString());
        verify(userRepository).findByEmail(anyString());
    }

    @Test
    void shouldThrowRegistrationExceptionUsernameAlreadyExists() {

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(RegistrationException.class,
                () -> {
                    userService.saveNewUserDto(userDto);
                });

        verify(userRepository).findByUsername(anyString());
        verify(userRepository).findByEmail(anyString());

        verify(userRepository, times(0)).save(any());
        verify(passwordEncoder, times(0)).encode(anyString());
        verify(userMapper, times(0)).userToUserDto(any(User.class));
        verify(userMapper, times(0)).userDtoToUser(any(UserDto.class));
    }

    @Test
    void shouldThrowRegistrationExceptionEmailAlreadyExists() {

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        assertThrows(RegistrationException.class,
                () -> {
                    userService.saveNewUserDto(userDto);
                });

        verify(userRepository).findByUsername(anyString());
        verify(userRepository).findByEmail(anyString());

        verify(userRepository, times(0)).save(any());
        verify(passwordEncoder, times(0)).encode(anyString());
        verify(userMapper, times(0)).userToUserDto(any(User.class));
        verify(userMapper, times(0)).userDtoToUser(any(UserDto.class));
    }
}
