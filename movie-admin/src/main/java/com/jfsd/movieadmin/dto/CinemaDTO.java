package com.jfsd.movieadmin.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class CinemaDTO {
    private Long id;
    private String name;
    private List<MovieDTO> movies;
}
