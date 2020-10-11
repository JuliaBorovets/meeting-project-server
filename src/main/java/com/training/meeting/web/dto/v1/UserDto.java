package com.training.meeting.web.dto.v1;

import com.training.meeting.web.dto.v1.validation.ValidPassword;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    @Email
    private String email;

    @Length(min = 8, max = 30)
    private String username;

    @ValidPassword
    private String password;

    private UserProfileDto userProfile;

}
