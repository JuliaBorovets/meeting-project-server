package com.training.meeting.web.mapper.v1;

import com.training.meeting.domain.user.User;
import com.training.meeting.web.dto.v1.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    @Mappings({
            @Mapping(target = "notifications", ignore = true),
            @Mapping(target = "interestedCategories", ignore = true),
            @Mapping(target = "organisationOrganizer", ignore = true),
            @Mapping(target = "organisationParticipant", ignore = true)
    })
    User userDtoToUser(UserDto userDto);
}
