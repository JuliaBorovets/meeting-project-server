package com.training.meeting.service;

import com.training.meeting.exception.UserCanNotFindException;
import com.training.meeting.web.dto.v1.UserProfileDto;

public interface UserProfileService {

    UserProfileDto updateUserProfile(UserProfileDto userProfileDto) throws UserCanNotFindException;
}
