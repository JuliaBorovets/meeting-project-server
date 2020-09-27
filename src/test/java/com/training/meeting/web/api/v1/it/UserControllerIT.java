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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
            .email("email")
            .password("password")
            .username("user")
           // .userProfile(UserProfileDto.builder().build())
            .build();

    @Test
    void shouldUpdateUserProfileWithUserRole() throws Exception {

        mockMvc.perform(put(UserController.BASE_URL)
                .with(httpBasic("user","password"))
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
                .with(httpBasic("user","password")))
                .andExpect(status().isForbidden());
    }
}
