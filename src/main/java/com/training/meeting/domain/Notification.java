package com.training.meeting.domain;

import com.training.meeting.domain.user.User;
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
@Table(name = "notification")
public class Notification extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String text;

    private Boolean isRead;
}
