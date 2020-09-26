package com.training.meeting.web.api.v1.it;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.Stream;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

public abstract class BaseIT {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    PasswordEncoder passwordEncoder;

    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

    public static Stream<Arguments> getStreamAllUsers() {
        return Stream.of(
                Arguments.of("user" , "$2a$10$0bvGW98WNf7QlAxdA42tt.ck6.Kjfhm1ItMbPu4JUjdIxkeR2jrhK"),
                Arguments.of("admin", "$2a$10$54SMDG8g8arB4IuBCSWfwe3OG2Hm0Qj8R.SAZDT9WD/YUWGdaEAJC")
        );
    }

    public static Stream<Arguments> getStreamAdmin() {
        return Stream.of(
                Arguments.of("admin", "$2a$10$54SMDG8g8arB4IuBCSWfwe3OG2Hm0Qj8R.SAZDT9WD/YUWGdaEAJC")
        );
    }
}
