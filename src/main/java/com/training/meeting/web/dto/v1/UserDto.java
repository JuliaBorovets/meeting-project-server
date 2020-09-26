package com.training.meeting.web.dto.v1;

import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String email;

    private String username;

    private String password;

    private UserProfileDto userProfile;

}
