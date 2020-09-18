package com.training.meeting.domain.reaction;

import com.training.meeting.domain.Organisation;
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
@Table(name = "organisation_reaction")
public class OrganisationReaction extends BaseReaction {

    @ManyToOne(fetch = FetchType.LAZY)
    private Organisation organisation;

}
