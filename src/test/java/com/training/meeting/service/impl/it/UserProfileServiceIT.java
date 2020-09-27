package com.training.meeting.service.impl.it;

import com.training.meeting.domain.user.UserProfile;
import com.training.meeting.exception.UserProfileCanNotFindException;
import com.training.meeting.repository.user.UserProfileRepository;
import com.training.meeting.service.impl.UserProfileServiceImpl;
import com.training.meeting.web.dto.v1.UserProfileDto;
import com.training.meeting.web.mapper.v1.UserProfileMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserProfileServiceIT {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserProfileMapper userProfileMapper;

    @Autowired
    UserProfileServiceImpl userProfileService;


    @Test
    void shouldUpdateUserProfile() throws UserProfileCanNotFindException {
        UserProfileDto userProfileDto = UserProfileDto.builder()
                .id(1L)
                .firstName("first name2")
                .lastName("last name2")
                .birthday(LocalDate.now().toString())
                .bio("bio2")
                //todo   .image(new Byte[20])
                .phoneNumber("0999999992")
                .build();

        userProfileService.updateUserProfile(userProfileDto);

        UserProfile resultFromDB = userProfileRepository.findById(userProfileDto.getId())
                .orElseThrow(UserProfileCanNotFindException::new);

        assertEquals(userProfileDto.getId(), resultFromDB.getId());
        assertEquals(userProfileDto.getFirstName(), resultFromDB.getFirstName());
        assertEquals(userProfileDto.getLastName(), resultFromDB.getLastName());
        assertEquals(userProfileDto.getBirthday(), resultFromDB.getBirthday().toString());
        assertEquals(userProfileDto.getBio(), resultFromDB.getBio());
        assertEquals(userProfileDto.getPhoneNumber(), resultFromDB.getPhoneNumber());
    }
}
