package com.training.meeting.domain.user;

import com.training.meeting.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "authority")
public class Authority extends BaseEntity {

    private String permission;

    @Builder.Default
    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles = Set.of();
}
