package com.training.meeting.web.api.v1;

import com.training.meeting.service.UserProfileService;
import com.training.meeting.web.dto.v1.UserProfileDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserProfileControllerTest extends AbstractRestControllerTest {

    @Mock
    UserProfileService userProfileService;

    @InjectMocks
    UserProfileController userProfileController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(userProfileController)
                .build();
    }

    @Test
    void shouldUpdateUserProfile() throws Exception {
        UserProfileDto userProfileDto = UserProfileDto.builder()
                .id(1L)
                .firstName("first name")
                .lastName("last name")
                .birthday(LocalDate.now().toString())
                .bio("bio")
                .image(new Byte[20])
                .phoneNumber("099999999")
                .build();

        mockMvc.perform(put(UserProfileController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userProfileDto))
        )
                .andExpect(status().isOk());

        verify(userProfileService).updateUserProfile(any(UserProfileDto.class));
    }


}
