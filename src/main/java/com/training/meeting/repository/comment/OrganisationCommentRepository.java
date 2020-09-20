package com.training.meeting.repository.comment;

import com.training.meeting.domain.comment.OrganisationComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisationCommentRepository extends JpaRepository<OrganisationComment, Long> {
}
