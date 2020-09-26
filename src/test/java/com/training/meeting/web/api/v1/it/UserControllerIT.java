package com.training.meeting.web.api.v1.it;

import com.training.meeting.repository.user.UserRepository;
import com.training.meeting.service.UserService;
import com.training.meeting.web.api.v1.UserController;
import com.training.meeting.web.dto.v1.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static com.training.meeting.web.api.v1.AbstractRestControllerTest.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerIT extends BaseIT {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    UserDto userDto = UserDto.builder().username("username").password("password").email("email").build();

    @Transactional
    @Test
    public void createUser() throws Exception {
        mockMvc.perform(post(UserController.BASE_URL + "/reg")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto))
        )
                .andExpect(status().isCreated());

        userRepository.deleteByUsername(userDto.getUsername());
    }

}
