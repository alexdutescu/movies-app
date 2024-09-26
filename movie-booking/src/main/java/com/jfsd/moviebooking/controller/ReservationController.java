package com.jfsd.moviebooking.controller;

import com.jfsd.moviebooking.model.Movie;
import com.jfsd.moviebooking.model.Reservation;
import com.jfsd.moviebooking.service.MovieService;
import com.jfsd.moviebooking.service.ReservationService;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final MovieService movieService;
    private final ReservationService reservationService;

    @GetMapping("/movies")
    public ResponseEntity<Set<Movie>> listMovies() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @PostMapping("/makeReservation")
    public ResponseEntity<Reservation> makeReservation(@RequestBody Reservation reservation) {
        try {
            return ResponseEntity.ok(reservationService.makeReservation(reservation));
        } catch (BadRequestException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
