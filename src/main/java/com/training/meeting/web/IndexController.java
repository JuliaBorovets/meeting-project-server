package com.training.meeting.web;

import com.training.meeting.config.security.permission.user.UserDeletePermission;
import com.training.meeting.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class IndexController {

    @GetMapping({"", "/"})
    public String index(){
        return "index";
    }

    @UserDeletePermission
    @GetMapping("/test")
    public String test(@AuthenticationPrincipal User user){
        log.info(user.getRoles().toString());
        log.info(user.getAuthorities().toString());
        log.info(user.getUsername() + "------------------");
        return "test";
    }
}
