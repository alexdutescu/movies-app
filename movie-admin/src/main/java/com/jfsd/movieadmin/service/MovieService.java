package com.jfsd.movieadmin.service;

import com.jfsd.movieadmin.dto.MovieDTO;
import com.jfsd.movieadmin.model.Movie;
import com.jfsd.movieadmin.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Set<Movie> getAll() {
        return new HashSet<>(movieRepository.findAll());
    }
    public Set<Movie> getByIds(Set<Long> ids) {
        return new HashSet<>(movieRepository.findAllById(ids));
    }
    public Movie getById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }


}