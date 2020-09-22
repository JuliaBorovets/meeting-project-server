package com.training.meeting.service;

import com.training.meeting.domain.reaction.Rating;

import java.util.List;
import java.util.Map;

public interface RatingService {
    Map<Rating, Double> getAverageRating(List<Rating> ratings);
}
