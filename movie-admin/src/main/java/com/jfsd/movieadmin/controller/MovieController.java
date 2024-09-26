package com.jfsd.movieadmin.controller;

import com.jfsd.movieadmin.dto.MovieDTO;
import com.jfsd.movieadmin.mapper.MovieMapper;
import com.jfsd.movieadmin.model.Movie;
import com.jfsd.movieadmin.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Set<Movie>> getAll() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDTO movieDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.saveMovie(movieMapper.toEntity(movieDTO)));
    }

    @PutMapping
    public ResponseEntity<Movie> editMovie(@RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(movieService.saveMovie(movieMapper.toEntity(movieDTO)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteById(id);
    }
}
