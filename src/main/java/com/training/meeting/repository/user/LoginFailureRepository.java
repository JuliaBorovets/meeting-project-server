package com.training.meeting.repository.user;

import com.training.meeting.domain.user.LoginFailure;
import com.training.meeting.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface LoginFailureRepository extends JpaRepository<LoginFailure, Integer> {

    List<LoginFailure> findAllByUserAndCreationDateIsAfter(User user, Timestamp timestamp);
}
