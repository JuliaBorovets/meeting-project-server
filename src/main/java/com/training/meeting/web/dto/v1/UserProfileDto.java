package com.training.meeting.web.dto.v1;

import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String birthday;

    private String bio;

    private Byte[] image;

    private String phoneNumber;
}
