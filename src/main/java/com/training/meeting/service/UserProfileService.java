package com.training.meeting.service;

import com.training.meeting.exception.UserProfileCanNotFindException;
import com.training.meeting.web.dto.v1.UserProfileDto;

public interface UserProfileService {

    UserProfileDto updateUserProfile(UserProfileDto userProfileDto) throws UserProfileCanNotFindException;
}
