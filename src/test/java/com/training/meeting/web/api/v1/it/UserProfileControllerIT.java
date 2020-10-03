package com.training.meeting.web.api.v1.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.meeting.service.UserProfileService;
import com.training.meeting.web.api.v1.UserProfileController;
import com.training.meeting.web.dto.v1.UserProfileDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static com.training.meeting.web.api.v1.AbstractRestControllerTest.asJsonString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserProfileControllerIT extends BaseIT {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserProfileService userProfileService;

    @Test
    void shouldUpdateUserProfile() throws Exception {

        UserProfileDto userProfileDto = UserProfileDto.builder()
                .id(1L)
                .firstName("first name")
                .lastName("last name")
                .birthday(LocalDate.now().toString())
                .bio("bio")
             //todo   .image(new Byte[20])
                .phoneNumber("099999999")
                .build();

        mockMvc.perform(put(UserProfileController.BASE_URL)
                .with(httpBasic("username","password12A"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userProfileDto))
        )
                .andExpect(status().isOk());
    }
}
