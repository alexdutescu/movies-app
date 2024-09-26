package com.jfsd.movieadmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @GetMapping("/price")
    public Double calculatePrice() {
        return 155.5;
    }
}
