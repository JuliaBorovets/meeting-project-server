package com.training.meeting.domain.user;

import com.training.meeting.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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

    @ManyToMany(mappedBy = "organisationOrganizer")
    private Set<User> organizers = Set.of();

    @ManyToMany(mappedBy = "organisationParticipant")
    private Set<User> participants = Set.of();


    //private Set<OrganisationComment> organisationComments = Set.of();

    //private Set<OrganisationReaction> organisationReactions = Set.of();

    //private Set<Event> events = Set.of();
    
}
