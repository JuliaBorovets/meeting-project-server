package com.training.meeting.web.mapper.v1;

import com.training.meeting.domain.user.User;
import com.training.meeting.domain.user.UserProfile;
import com.training.meeting.web.dto.v1.UserProfileDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class UserProfileMapperTest {

    @Autowired
    UserProfileMapper userProfileMapper;

    final Long ID = 1L;
    final String FIRST_NAME = "first name";
    final String LAST_NAME = "last name";
    final String BIRTHDAY = LocalDate.now().toString();
    final String BIO = "bio";
    final Byte[] IMAGE = new Byte[2];
    final String PHONE_NUMBER = "0999999999";

    UserProfileDto userProfileDto;
    UserProfile userProfile;

    @BeforeEach
    void setUp() {
        userProfileDto = UserProfileDto.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .birthday(BIRTHDAY)
                .bio(BIO)
                .image(IMAGE)
                .phoneNumber(PHONE_NUMBER)
                .build();
        userProfile = UserProfile.builder()
                .user(User.builder().build())
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .birthday(LocalDate.parse(BIRTHDAY))
                .bio(BIO)
                .image(IMAGE)
                .phoneNumber(PHONE_NUMBER)
                .build();

    }

    @Test
    void userProfileDtoToUserProfile() {

        UserProfile result = userProfileMapper.userProfileDtoToUserProfile(userProfileDto);

        assertEquals(ID, result.getId());
        assertEquals(FIRST_NAME, result.getFirstName());
        assertEquals(LAST_NAME, result.getLastName());
        assertEquals(BIRTHDAY, result.getBirthday().toString());
        assertEquals(BIO, result.getBio());
        assertEquals(IMAGE.length, result.getImage().length);
        assertEquals(PHONE_NUMBER, result.getPhoneNumber());
        assertNull(result.getUser());
    }

    @Test
    void userProfileToUserProfileDto() {

        UserProfileDto result = userProfileMapper.userProfileToUserProfileDto(userProfile);

        assertEquals(ID, result.getId());
        assertEquals(FIRST_NAME, result.getFirstName());
        assertEquals(LAST_NAME, result.getLastName());
        assertEquals(BIRTHDAY, result.getBirthday().toString());
        assertEquals(BIO, result.getBio());
        assertEquals(IMAGE.length, result.getImage().length);
        assertEquals(PHONE_NUMBER, result.getPhoneNumber());
    }
}
