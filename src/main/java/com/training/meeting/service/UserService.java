package com.training.meeting.service;

import com.training.meeting.exception.RegistrationException;
import com.training.meeting.web.dto.v1.UserDto;

public interface UserService {

    UserDto saveNewUserDto(UserDto userDto) throws RegistrationException;

}
