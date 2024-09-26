package com.jfsd.moviebooking.service;

import com.jfsd.moviebooking.model.Movie;
import com.jfsd.moviebooking.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Set<Movie> getAll() {
        return new HashSet<>(movieRepository.findAll());
    }
}
