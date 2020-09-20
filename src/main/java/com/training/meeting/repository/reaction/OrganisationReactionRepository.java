package com.training.meeting.repository.reaction;

import com.training.meeting.domain.reaction.OrganisationReaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisationReactionRepository extends JpaRepository<OrganisationReaction, Long> {
}
