package com.training.meeting.repository.user;

import com.training.meeting.domain.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<UserProfile, Long> {
}
