package com.training.meeting.web;

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

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal User user){
        log.info(user.getUsername() + "------------------");
        return "test";
    }
}
