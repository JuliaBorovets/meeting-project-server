package com.training.meeting.domain.user;

import com.training.meeting.domain.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
@Table(name = "user_profile")
public class UserProfile extends BaseEntity {

    @OneToOne(mappedBy = "userProfile")
    private User user;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private String bio;

    @Lob
    private Byte[] image;

    private String phoneNumber;

}
