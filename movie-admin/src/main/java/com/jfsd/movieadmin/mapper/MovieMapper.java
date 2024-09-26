package com.jfsd.movieadmin.mapper;

import com.jfsd.movieadmin.dto.CinemaDTO;
import com.jfsd.movieadmin.dto.MovieDTO;
import com.jfsd.movieadmin.enums.MovieType;
import com.jfsd.movieadmin.model.Movie;
import com.jfsd.movieadmin.service.CinemaService;
import com.jfsd.movieadmin.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class MovieMapper {

    private final MovieService movieService;
    private final CinemaService cinemaService;

    public Movie toEntity(MovieDTO dto) {
        Movie movie;
        try {
            movie = new Movie();
            if (dto.getId() != null) {
                movie = movieService.getById(dto.getId());
            }
            if (dto.getMovieType() != null) {
                movie.setMovieType(MovieType.of(dto.getMovieType()));
            }
            if (dto.getName() != null) {
                movie.setName(dto.getName());
            }
            if (dto.getCinemas() != null) {
                movie.setCinemas(cinemaService.getByIds(
                        dto.getCinemas().stream().map(CinemaDTO::getId).collect(Collectors.toSet())
                ));
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
        return movie;
    }

    public MovieDTO toDto(Movie movie) {
        Set<CinemaDTO> cinemas = null;
        if (movie.getCinemas() != null && movie.getCinemas().size() > 0) {
            cinemas = movie.getCinemas().stream()
                    .map(c -> CinemaDTO.builder()
                            .id(c.getId())
                            .name(c.getName())
                            .build())
                    .collect(Collectors.toSet());
        }
        return MovieDTO.builder()
                .id(movie.getId())
                .name(movie.getName())
                .movieType(movie.getMovieType().getType())
                .cinemas(cinemas)
                .build();
    }
}
