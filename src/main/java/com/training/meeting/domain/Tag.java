package com.training.meeting.domain;

import com.training.meeting.domain.event.Event;
import com.training.meeting.domain.user.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "tag")
public class  Tag  extends BaseEntity {

    private String name;

    @Builder.Default
    @ManyToMany(mappedBy = "interestedCategories")
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tag_event",
            joinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id", referencedColumnName = "id")})
    private List<Event> events = new ArrayList<>();
}
