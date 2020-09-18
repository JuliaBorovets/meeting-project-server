package com.training.meeting.domain.event;

import com.training.meeting.domain.BaseEntity;
import com.training.meeting.domain.Location;
import com.training.meeting.domain.Organisation;
import com.training.meeting.domain.Tag;
import com.training.meeting.domain.comment.EventComment;
import com.training.meeting.domain.reaction.EventReaction;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "event")
public class Event extends BaseEntity {

    private String name;

    private LocalDate beginDate;

    private LocalDate endDate;

    private Long maxNumberParticipants;

    @Lob
    private Byte[] photo;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Builder.Default
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventReaction> reactions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventComment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Organisation organisation;

    @Builder.Default
    @ManyToMany(mappedBy = "events")
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;
}
