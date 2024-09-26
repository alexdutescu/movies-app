package com.jfsd.movieadmin.bean;

import com.jfsd.movieadmin.service.LoginService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginBean {

    private final LoginService loginService;

    @PostConstruct
    private void init() {
        loginService.initLogin();
    }
}
