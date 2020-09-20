package com.training.meeting.repository.comment;

import com.training.meeting.domain.comment.EventComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCommentRepository extends JpaRepository<EventComment, Long> {
}
