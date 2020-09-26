package com.training.meeting.web.api.v1;

import com.training.meeting.config.security.permission.user.UserUpdatePermission;
import com.training.meeting.exception.RegistrationException;
import com.training.meeting.exception.RoleCanNotFindException;
import com.training.meeting.exception.UserCanNotFindException;
import com.training.meeting.service.UserService;
import com.training.meeting.web.dto.v1.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/api/v1/user";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto) throws RegistrationException, RoleCanNotFindException {
        return userService.saveNewUserDto(userDto);
    }

    @UserUpdatePermission
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@Valid @RequestBody UserDto userDto) throws UserCanNotFindException {
        return userService.updateUserDto(userDto);
    }

    @UserUpdatePermission
    @PutMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public void test(@Valid @RequestBody UserDto userDto) throws UserCanNotFindException {
         userService.updateUserDto(userDto);
        //todo
        log.info("-------------------------------------------");
    }

}
