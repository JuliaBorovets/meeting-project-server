package com.training.meeting.domain.comment;

import com.training.meeting.domain.Event;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "event_comments")
public class EventComment extends BaseComment{

    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;
}
