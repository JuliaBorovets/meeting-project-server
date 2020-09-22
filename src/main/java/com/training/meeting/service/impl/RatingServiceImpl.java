package com.training.meeting.service.impl;

import com.training.meeting.domain.reaction.Rating;
import com.training.meeting.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RatingServiceImpl implements RatingService {
    @Override
    public Map<Rating, Double> getAverageRating(List<Rating> ratings){
        Map<Rating, Double> result = new HashMap<>();

//        double average =  ratings.stream()
//                .mapToInt(Rating::getScore)
//                .average()
//                .orElseThrow(() -> new RuntimeException("no ratings!"));
//
//        Rating rating = Arrays.stream(Rating.values())
//                .min(Comparator.comparingDouble(i -> Math.abs(i.getScore() - average)))
//                .orElseThrow(() -> new RuntimeException("no ratings!"));
//        result.put(rating, average);
        return result;
    }
}
