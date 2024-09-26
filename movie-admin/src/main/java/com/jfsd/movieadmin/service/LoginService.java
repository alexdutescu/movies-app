package com.jfsd.movieadmin.service;

import com.jfsd.movieadmin.model.Login;
import com.jfsd.movieadmin.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    @Transactional
    public void initLogin() {
        Login login = Login.builder()
                .email("dutescu.alexandru@gmail.com")
                .password("test")
                .typeOfUser("admin")
                .build();
        Optional<Login> result  = loginRepository.findById(login.getEmail());
        if (result.isPresent()) {
            log.info("Account already present");
        } else {
            loginRepository.save(login);
            log.error("Admin account created");
        }
    }

    public Login checkLogin(String email, String password) throws NoSuchElementException {
        return loginRepository.findByEmailAndPassword(email, password).orElseThrow();
    }
}
