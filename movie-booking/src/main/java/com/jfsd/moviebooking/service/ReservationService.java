package com.jfsd.moviebooking.service;

import com.jfsd.moviebooking.config.ApplicationConfig;
import com.jfsd.moviebooking.dto.ReservationDTO;
import com.jfsd.moviebooking.model.Cinema;
import com.jfsd.moviebooking.model.Movie;
import com.jfsd.moviebooking.model.Reservation;
import com.jfsd.moviebooking.repository.CinemaRepository;
import com.jfsd.moviebooking.repository.MovieRepository;
import com.jfsd.moviebooking.repository.ReservationRepository;
import jakarta.ws.rs.BadRequestException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final ApplicationConfig.MovieAdminProperties restTemplateProperties;

    public ReservationService(ReservationRepository reservationRepository, MovieRepository movieRepository, CinemaRepository cinemaRepository, ApplicationConfig.MovieAdminProperties restTemplateProperties) {
        this.reservationRepository = reservationRepository;
        this.movieRepository = movieRepository;
        this.cinemaRepository = cinemaRepository;
        this.restTemplateProperties = restTemplateProperties;
    }

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    public ReservationDTO makeReservation(ReservationDTO reservation) throws Exception {
        //send params: movieId, cinemaId, dateTime
        String uriParams = "/reservations/price";
//        ResponseEntity<Double> responsePrice = restTemplateProperties.getRestTemplate().getForEntity(restTemplateProperties.getUrl().concat(uriParams), Double.class);
//        if (responsePrice.getStatusCode().value() != HttpStatus.OK.value()) {
//            throw new BadRequestException("No available movies for specified selection");
//        }
        // TODO: 9/21/2024 make request to retrieve reservation price

//        reservation.setPrice(responsePrice.getBody());
        reservation.setPrice(100d);
        Movie movie = movieRepository.findById(reservation.getMovieId()).orElse(null);
        Cinema cinema = cinemaRepository.findById(reservation.getCinemaId()).orElse(null);

        Reservation resDb = new Reservation();
        resDb.setPrice(reservation.getPrice());
        resDb.setMovie(movie);
        resDb.setCinema(cinema);
        resDb.setEmail(reservation.getEmail());
        resDb.setReservationDate(reservation.getReservationDate());
        resDb.setCreatedDate(ZonedDateTime.now());

        reservationRepository.save(resDb);

        return reservation;
    }

    public void deleteReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    public void deleteReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    public Set<String> getAvailableLocations() {
        ParameterizedTypeReference<Set<String>> setReference = new ParameterizedTypeReference<>() {};
        ResponseEntity<Set<String>> locationsResponse = restTemplateProperties.getRestTemplate().exchange(restTemplateProperties.getUrl().concat("/locations"), HttpMethod.GET,null, setReference);
        if(locationsResponse.getStatusCode().isError()) {
            System.out.println("Failed to retrieve locations: "+ locationsResponse.getBody());
            return new HashSet<>();
        }

        return locationsResponse.getBody();
    }
}
