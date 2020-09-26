package com.training.meeting.bootstrap;

import com.training.meeting.domain.user.Authority;
import com.training.meeting.domain.user.Role;
import com.training.meeting.domain.user.User;
import com.training.meeting.domain.user.UserProfile;
import com.training.meeting.repository.user.AuthorityRepository;
import com.training.meeting.repository.user.RoleRepository;
import com.training.meeting.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Component
public class MeetingBootstrap implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.count() == 0) {
            loadSecurityData();
        }
    }

    private void loadSecurityData() {

        Authority userCreate = authorityRepository.save(Authority.builder().permission("user.create").build());
        Authority userUpdate = authorityRepository.save(Authority.builder().permission("user.update").build());
        Authority userDelete = authorityRepository.save(Authority.builder().permission("user.delete").build());

        Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
        Role userRole = roleRepository.save(Role.builder().name("USER").build());


        userRole.getAuthorities().addAll(Arrays.asList(userCreate, userUpdate));
        adminRole.getAuthorities().addAll(Arrays.asList(userCreate, userUpdate, userDelete));

        userRole.getAuthorities().add(userCreate);
        userRole.getAuthorities().add(userUpdate);

        roleRepository.save(userRole);
        roleRepository.save(adminRole);


        User user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .email("useremail@gmail.com")
                .userProfile(UserProfile.builder().build())
                .build();
        user.getRoles().add(userRole);

        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .email("adminemail@gmail.com")
                .userProfile(UserProfile.builder().build())
                .build();
      //  admin.getRoles().add(userRole);
        admin.getRoles().add(adminRole);

        userRepository.save(user);
        userRepository.save(admin);

        log.info("Users Loaded: " + userRepository.count());
    }
}
