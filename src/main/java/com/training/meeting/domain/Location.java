package com.training.meeting.domain;

import com.training.meeting.domain.event.Event;
import com.training.meeting.domain.user.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "location")
public class Location extends BaseEntity {

    private String country;

    private String city;

    @Builder.Default
    @OneToMany(mappedBy = "location")
    private List<User> users = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "location")
    private List<Event> events = new ArrayList<>();
}
