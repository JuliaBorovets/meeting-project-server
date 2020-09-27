package com.training.meeting.service.impl;


import com.training.meeting.domain.user.UserProfile;
import com.training.meeting.exception.UserProfileCanNotFindException;
import com.training.meeting.repository.user.UserProfileRepository;
import com.training.meeting.service.UserProfileService;
import com.training.meeting.web.dto.v1.UserProfileDto;
import com.training.meeting.web.mapper.v1.UserProfileMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, UserProfileMapper userProfileMapper) {
        this.userProfileRepository = userProfileRepository;
        this.userProfileMapper = userProfileMapper;
    }

    @Transactional
    @Override
    public UserProfileDto updateUserProfile(UserProfileDto userProfileDto) throws UserProfileCanNotFindException {

        UserProfile userProfile = findUserProfileById(userProfileDto.getId());

        userProfile.setFirstName(userProfileDto.getFirstName());
        userProfile.setLastName(userProfileDto.getLastName());
        userProfile.setBirthday(LocalDate.parse(userProfileDto.getBirthday()));
        userProfile.setBio(userProfileDto.getBio());
        userProfile.setImage(userProfileDto.getImage());
        userProfile.setPhoneNumber(userProfileDto.getPhoneNumber());

        return userProfileMapper.userProfileToUserProfileDto(
                userProfileRepository.save(userProfile)
        );
    }

    private UserProfile findUserProfileById(Long id) throws UserProfileCanNotFindException {
        return userProfileRepository.findById(id)
                .orElseThrow(UserProfileCanNotFindException::new);
    }
}
