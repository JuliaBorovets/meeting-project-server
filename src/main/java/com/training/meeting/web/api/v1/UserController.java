package com.training.meeting.web.api.v1;

import com.training.meeting.exception.RegistrationException;
import com.training.meeting.service.UserService;
import com.training.meeting.web.dto.v1.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/api/v1/user";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/reg")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto) throws RegistrationException {

        return userService.saveNewUserDto(userDto);
    }

}
