package com.training.meeting.domain.comment;

import com.training.meeting.domain.Organisation;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
@SuperBuilder
@Table(name = "organisation_comment")
public class OrganisationComment extends BaseComment {

    @ManyToOne(fetch = FetchType.LAZY)
    private Organisation organisation;

}
