package com.jfsd.movieadmin.repository;

import com.jfsd.movieadmin.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    void deleteById(Long id);
}
