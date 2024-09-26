package com.jfsd.movieadmin.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class MovieDTO {

    private Long id;
    private String name;
    private Set<CinemaDTO> cinemas;
    private String movieType;
}