package com.training.meeting.domain.reaction;

import com.training.meeting.domain.BaseEntity;
import com.training.meeting.domain.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class BaseReaction extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private Rating rating;

    @Transient
    private Map<Rating, Double> averageRating = new HashMap<>();
}
