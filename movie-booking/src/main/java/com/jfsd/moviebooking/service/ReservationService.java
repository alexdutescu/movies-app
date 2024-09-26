package com.jfsd.moviebooking.service;

import com.jfsd.moviebooking.config.ApplicationConfig;
import com.jfsd.moviebooking.model.Reservation;
import com.jfsd.moviebooking.repository.ReservationRepository;
import jakarta.ws.rs.BadRequestException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ApplicationConfig.MovieAdminProperties restTemplateProperties;

    public ReservationService(ReservationRepository reservationRepository, ApplicationConfig.MovieAdminProperties restTemplateProperties) {
        this.reservationRepository = reservationRepository;
        this.restTemplateProperties = restTemplateProperties;
    }

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation makeReservation(Reservation reservation) throws Exception {
        //send params: movieId, cinemaId, dateTime
        String uriParams = "/reservations/price";
        ResponseEntity<Double> responsePrice = restTemplateProperties.getRestTemplate().getForEntity(restTemplateProperties.getUrl().concat(uriParams), Double.class);
        if (responsePrice.getStatusCode().value() != HttpStatus.OK.value()) {
            throw new BadRequestException("No available movies for specified selection");
        }
        // TODO: 9/21/2024 make request to retrieve reservation price
        reservation.setPrice(responsePrice.getBody());

        return reservationRepository.save(reservation);
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
