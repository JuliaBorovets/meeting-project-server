package com.training.meeting.web.api.v1;

import com.training.meeting.exception.handler.ControllerExceptionHandler;
import com.training.meeting.service.UserService;
import com.training.meeting.web.dto.v1.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest extends AbstractRestControllerTest{

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void shouldCreateUser() throws Exception {
        UserDto userDto = UserDto.builder().username("username").password("passw12ordA").email("email@gmail.com").build();

        mockMvc.perform(post(UserController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto))
        )
                .andExpect(status().isCreated());

        verify(userService).saveNewUserDto(any(UserDto.class));
    }

    @Test
    void shouldNotCreateUser() throws Exception {
        UserDto userDto = UserDto.builder().username("username").password("password").email("email").build();

        mockMvc.perform(post(UserController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto))
        )
                .andExpect(status().isBadRequest());

        verify(userService, times(0)).saveNewUserDto(any(UserDto.class));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        UserDto userDto = UserDto.builder().username("username").password("passw12ordA").email("email@gmail.com").build();

        mockMvc.perform(put(UserController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto))
        )
                .andExpect(status().isOk());

        verify(userService).updateUserDto(any(UserDto.class));
    }

    @Test
    void shouldNotUpdateUser() throws Exception {
        UserDto userDto = UserDto.builder().username("username").password("password").email("email@gmail.com").build();

        mockMvc.perform(put(UserController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto))
        )
                .andExpect(status().isBadRequest());

        verify(userService, times(0)).updateUserDto(any(UserDto.class));
    }

    @Test
    void shouldDeleteUser() throws Exception {

        mockMvc.perform(delete(UserController.BASE_URL + "/3"))
                .andExpect(status().isOk());

        verify(userService).deleteUser(anyLong());
    }
}
