package com.training.meeting.service;

import com.training.meeting.exception.RegistrationException;
import com.training.meeting.exception.RoleCanNotFindException;
import com.training.meeting.exception.UserCanNotFindException;
import com.training.meeting.web.dto.v1.UserDto;

public interface UserService {

    UserDto saveNewUserDto(UserDto userDto) throws RegistrationException, RoleCanNotFindException;

    UserDto updateUserDto(UserDto userDto) throws UserCanNotFindException;

    void deleteUser(Long id);

}
