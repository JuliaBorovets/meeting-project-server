package com.training.meeting.domain;

import com.training.meeting.domain.comment.OrganisationComment;
import com.training.meeting.domain.reaction.OrganisationReaction;
import com.training.meeting.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "organisations")
public class Organisation extends BaseEntity {

    private String name;

    @Builder.Default
    private Boolean isPrivate = false;

    private String info;

    @Lob
    private Byte[] photo;

    @Builder.Default
    @ManyToMany(mappedBy = "organisationOrganizer")
    private Set<User> organizers = Set.of();

    @Builder.Default
    @ManyToMany(mappedBy = "organisationParticipant")
    private Set<User> participants = Set.of();

    @Builder.Default
    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrganisationComment> organisationComments = Set.of();

    @Builder.Default
    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrganisationReaction> organisationReactions = Set.of();

    //private Set<Event> events = Set.of();

}
