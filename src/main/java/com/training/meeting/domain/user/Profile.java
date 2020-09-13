package com.training.meeting.domain.user;

import com.training.meeting.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {

    @OneToOne(mappedBy = "profile")
    private User user;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private String bio;

    @Lob
    private Byte[] image;

    private String phoneNumber;

}
