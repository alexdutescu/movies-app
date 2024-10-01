package com.jfsd.moviebooking.dto;

import lombok.Data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
public class ReservationDTO implements java.io.Serializable {

    private String email;
    private Long movieId;
    private Long cinemaId;
    private Long reservationDateFe;
    private double price;

    public ZonedDateTime getReservationDate() {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(reservationDateFe), ZoneId.systemDefault());
    }
}
