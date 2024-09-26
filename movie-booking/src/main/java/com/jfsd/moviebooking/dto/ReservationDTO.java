package com.jfsd.moviebooking.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ReservationDTO implements java.io.Serializable {

    private String email;
    private Long movieId;
    private Long cinemaId;
    private ZonedDateTime reservationDate;
    private double price;

}
