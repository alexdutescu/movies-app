package com.jfsd.movieadmin.service;

import com.jfsd.movieadmin.model.Reservation;
import com.jfsd.movieadmin.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }
    public void deleteReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
    }
    public void deleteReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
