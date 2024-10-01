package com.jfsd.movieadmin.controller;

import com.jfsd.movieadmin.dto.CinemaDTO;
import com.jfsd.movieadmin.model.Cinema;
import com.jfsd.movieadmin.service.CinemaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/cinemas")
public class CinemaController {
    
    private final CinemaService cinemaService;

    @GetMapping("/{id}")
    public ResponseEntity<Cinema> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cinemaService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Set<Cinema>> getAll() {
        return ResponseEntity.ok(cinemaService.getAll());
    }

    @PostMapping
    public ResponseEntity<Cinema> addCinema(@RequestBody CinemaDTO cinemaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cinemaService.saveCinema(cinemaDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCinema(@PathVariable Long id) {
        cinemaService.deleteById(id);
    }
}
