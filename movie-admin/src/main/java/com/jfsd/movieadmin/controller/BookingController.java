//package com.jfsd.moviebooking.controller;
//
//import com.jfsd.moviebooking.enums.MovieType;
//import com.jfsd.moviebooking.model.Reservation;
//import jakarta.ws.rs.BadRequestException;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;


//@Controller
//public class BookingController {
//
//    private final BookingService bookingService;
//
//    public BookingController(BookingService bookingService) {
//        this.bookingService = bookingService;
//    }
//
//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public String index() {
//        return "index";
//    }
//
//    @RequestMapping(value = "viewBookings", method = RequestMethod.GET)
//    public String viewBookings(Model model) {
//        List<Reservation> listOfBookings = bookingService.findAllReservations();
//        model.addAttribute("bookings", listOfBookings);
//        return "viewBookings";
//    }
//
//    @RequestMapping(value = "addBooking", method = RequestMethod.GET)
//    public String addBooking(Reservation reservation, Model model) {
//        Set<String> locationsStr = bookingService.getAvailableLocations();
//
//        model.addAttribute("fromCities", locationsStr);
//        model.addAttribute("toCities", locationsStr);
//        model.addAttribute("reservation", reservation);
//        model.addAttribute("movieTypes", MovieType.values());
//
//        return "addBooking";
//    }
//
//    @RequestMapping(value = "addBooking", method = RequestMethod.POST)
//    public String addBookingPost(Reservation reservation, Model model) {
//        System.out.println("reservation = " + reservation.toString());
//        try {
//            Reservation dbReservation = bookingService.makeReservation(reservation);
//            model.addAttribute("booking", dbReservation);
//
//            System.out.println("DB reservation = " + dbReservation.toString());
//            return "viewBooking";
//        } catch (Exception ex) {
//            model.addAttribute("errorMessage", ex.getMessage());
//            return "addBooking";
//        }
//    }
//
//}
