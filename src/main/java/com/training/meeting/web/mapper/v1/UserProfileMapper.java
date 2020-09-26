package com.training.meeting.web.mapper.v1;

import com.training.meeting.domain.user.UserProfile;
import com.training.meeting.web.dto.v1.UserProfileDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    UserProfile userProfileDtoToUserProfile(UserProfileDto userProfileDto);

    UserProfileDto userProfileToUserProfileDto(UserProfile userProfile);
}
