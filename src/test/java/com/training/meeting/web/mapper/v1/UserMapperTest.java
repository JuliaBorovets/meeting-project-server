package com.training.meeting.web.mapper.v1;

import com.training.meeting.domain.user.User;
import com.training.meeting.domain.user.UserProfile;
import com.training.meeting.web.dto.v1.UserDto;
import com.training.meeting.web.dto.v1.UserProfileDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    final Long USER_ID = 1L;
    final Long PROFILE_ID = 2L;

    @Test
    void userToUserDto() {
        UserProfile userProfile = UserProfile.builder().id(PROFILE_ID).build();

        User user = User.builder()
                .id(USER_ID)
                .userProfile(userProfile)
                .email("email")
                .password("password")
                .username("username").build();

        UserDto userDto = userMapper.userToUserDto(user);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getUserProfile().getId(), userDto.getUserProfile().getId());

    }

    @Test
    void userDtoToUser() {
        UserProfileDto userProfileDto = UserProfileDto.builder().id(PROFILE_ID).build();

        UserDto userDto = UserDto.builder()
                .id(USER_ID)
                .userProfile(userProfileDto)
                .email("email")
                .password("password")
                .username("username").build();

        User user = userMapper.userDtoToUser(userDto);

        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.getUsername(), user.getUsername());
        assertEquals(userDto.getUserProfile().getId(), user.getUserProfile().getId());
    }
}
