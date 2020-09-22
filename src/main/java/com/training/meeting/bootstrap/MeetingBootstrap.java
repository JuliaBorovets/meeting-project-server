package com.training.meeting.bootstrap;

import com.training.meeting.domain.user.Authority;
import com.training.meeting.domain.user.Profile;
import com.training.meeting.domain.user.Role;
import com.training.meeting.domain.user.User;
import com.training.meeting.repository.user.AuthorityRepository;
import com.training.meeting.repository.user.RoleRepository;
import com.training.meeting.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

       if (userRepository.count() == 0){
           loadSecurityData();
       }
    }

    private void loadSecurityData() {

        Authority create = authorityRepository.save(Authority.builder().permission("create").build());
        Authority read = authorityRepository.save(Authority.builder().permission("read").build());
        Authority update = authorityRepository.save(Authority.builder().permission("update").build());
        Authority delete = authorityRepository.save(Authority.builder().permission("delete").build());

        Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
        Role userRole = roleRepository.save(Role.builder().name("USER").build());

        adminRole.setAuthorities(new HashSet<>(Set.of(create, read, update, delete)));

        userRole.setAuthorities(new HashSet<>(Set.of(read)));

        roleRepository.saveAll(Arrays.asList(adminRole, userRole));

        userRepository.save(User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .roles(Set.of(adminRole))
                .email("email")
                .profile(Profile.builder().build())
                .build());

        log.info("Users Loaded: " + userRepository.count());
    }
}
