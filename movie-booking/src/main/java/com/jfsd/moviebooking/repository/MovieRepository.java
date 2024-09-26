package com.jfsd.moviebooking.repository;

import com.jfsd.moviebooking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
