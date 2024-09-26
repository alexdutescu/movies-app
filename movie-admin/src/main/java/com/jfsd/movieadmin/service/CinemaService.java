package com.jfsd.movieadmin.service;

import com.jfsd.movieadmin.dto.CinemaDTO;
import com.jfsd.movieadmin.dto.MovieDTO;
import com.jfsd.movieadmin.model.Cinema;
import com.jfsd.movieadmin.model.Movie;
import com.jfsd.movieadmin.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CinemaService {

    private final MovieService movieService;
    private final CinemaRepository cinemaRepository;

    public Cinema getById(Long id) {
        return cinemaRepository.findById(id).orElse(null);
    }

    public Set<Cinema> getByIds(Set<Long> ids) {
        return new HashSet<>(cinemaRepository.findAllById(ids));
    }

    public Set<Cinema> getAll() {
        return new HashSet<>(cinemaRepository.findAll());
    }

    public Cinema saveCinema(CinemaDTO cinemaDTO) {
        Set<Movie> movies = movieService.getByIds(
                cinemaDTO.getMovies().stream()
                        .map(MovieDTO::getId)
                        .collect(Collectors.toSet())
        );

        return cinemaRepository.save(Cinema.builder()
                        .name(cinemaDTO.getName())
                        .movies(movies.stream().toList())
                .build());
    }

    public void deleteById(Long id) {
        cinemaRepository.deleteById(id);
    }
}
