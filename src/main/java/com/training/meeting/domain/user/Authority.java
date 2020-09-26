package com.training.meeting.domain.user;

import com.training.meeting.domain.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Table(name = "authority")
public class Authority extends BaseEntity {

    private String permission;

    @Builder.Default
    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles = new HashSet<>();
}
