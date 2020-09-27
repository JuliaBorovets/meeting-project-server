package com.training.meeting.web.api.v1;

import com.training.meeting.config.security.permission.user.UserUpdatePermission;
import com.training.meeting.exception.UserProfileCanNotFindException;
import com.training.meeting.service.UserProfileService;
import com.training.meeting.web.dto.v1.UserProfileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(UserProfileController.BASE_URL)
public class UserProfileController {

    public static final String BASE_URL = "/api/v1/user/profile";

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @UserUpdatePermission
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserProfileDto updateUserProfile(@Valid @RequestBody UserProfileDto userProfileDto) throws UserProfileCanNotFindException {
        return userProfileService.updateUserProfile(userProfileDto);
    }
}
