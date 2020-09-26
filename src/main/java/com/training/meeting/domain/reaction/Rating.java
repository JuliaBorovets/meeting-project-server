package com.training.meeting.domain.reaction;

import com.training.meeting.domain.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "rating")
public class Rating extends BaseEntity {

    private String name;
    private int score;

}
