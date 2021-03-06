package com.training.meeting.web.api.v1.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.meeting.repository.user.UserRepository;
import com.training.meeting.service.UserService;
import com.training.meeting.web.api.v1.UserController;
import com.training.meeting.web.dto.v1.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.training.meeting.web.api.v1.AbstractRestControllerTest.asJsonString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerIT extends BaseIT{
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    UserDto userDto = UserDto.builder()
            .id(1L)
            .email("email@gmail.com")
            .password("password12A")
            .username("username")
           // .userProfile(UserProfileDto.builder().build())
            .build();

    @Test
    void shouldCreateUser() throws Exception {
        UserDto userDto = UserDto.builder()
            .email("email3@gmail.com")
            .password("password12A")
            .username("userrrrr3")
            .build();
        mockMvc.perform(post(UserController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto))
        )
                .andExpect(status().isCreated());
    }

    @Test
    void shouldNotCreateUser() throws Exception {
        UserDto userDto = UserDto.builder()
                .email("email3")
                .password("password")
                .username("user3")
                .build();
        mockMvc.perform(post(UserController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto))
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateUserProfileWithUserRole() throws Exception {

        mockMvc.perform(put(UserController.BASE_URL)
                .with(httpBasic("username","password12A"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto))
        )
                .andExpect(status().isOk());

    }

    @Test
    void shouldUpdateUserProfileWithAdminRole() throws Exception {

        mockMvc.perform(put(UserController.BASE_URL)
                .with(httpBasic("admin","password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto))
        )
                .andExpect(status().isOk());

    }

    @Test
    void shouldThrowExceptionNoAuth() throws Exception {

        mockMvc.perform(put(UserController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto))
        )
                .andExpect(status().isUnauthorized());

    }

    @Test
    void shouldDeleteUserAdminRole() throws Exception {
        mockMvc.perform(delete(UserController.BASE_URL + "/2")
                .with(httpBasic("admin","password")))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotDeleteUserUserRole() throws Exception {
        mockMvc.perform(delete(UserController.BASE_URL + "/2")
                .with(httpBasic("username","password12A")))
                .andExpect(status().isForbidden());
    }

}
