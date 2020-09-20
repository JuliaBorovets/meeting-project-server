package com.training.meeting.repository.reaction;

import com.training.meeting.domain.reaction.EventReaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventReactionRepository extends JpaRepository<EventReaction, Long> {
}
