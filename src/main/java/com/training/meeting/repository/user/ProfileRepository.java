package com.training.meeting.repository.user;

import com.training.meeting.domain.user.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
