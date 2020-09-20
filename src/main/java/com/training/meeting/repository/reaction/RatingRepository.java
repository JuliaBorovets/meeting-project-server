package com.training.meeting.repository.reaction;

import com.training.meeting.domain.reaction.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
