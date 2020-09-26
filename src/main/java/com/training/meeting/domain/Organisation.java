package com.training.meeting.domain;

import com.training.meeting.domain.comment.OrganisationComment;
import com.training.meeting.domain.event.Event;
import com.training.meeting.domain.reaction.OrganisationReaction;
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
@Table(name = "organisation")
public class Organisation extends BaseEntity {

    private String name;

    @Builder.Default
    private Boolean isPrivate = false;

    private String info;

    @Lob
    private Byte[] photo;

    @Builder.Default
    @ManyToMany(mappedBy = "organisationOrganizer")
    private Set<User> organizers = new HashSet<>();

    @Builder.Default
    @ManyToMany(mappedBy = "organisationParticipant")
    private Set<User> participants = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrganisationComment> organisationComments = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrganisationReaction> organisationReactions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "organisation")
    private List<Event> events = new ArrayList<>();

}
