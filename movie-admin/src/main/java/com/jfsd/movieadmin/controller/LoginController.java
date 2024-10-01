package com.jfsd.movieadmin.controller;

import com.jfsd.movieadmin.dto.LoginDTO;
import com.jfsd.movieadmin.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/")
    public ResponseEntity<Object> checkLogin(@RequestBody LoginDTO login) {
        try{
            log.info("Login body: {}", login);
            return ResponseEntity.ok(loginService.checkLogin(login.getEmailid(), login.getPassword()));
        } catch (NoSuchElementException ex) {
            log.warn("Invalid credentials!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid credentials");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
