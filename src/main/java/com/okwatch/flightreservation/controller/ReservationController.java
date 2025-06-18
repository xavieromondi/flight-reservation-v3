package com.okwatch.flightreservation.controller;

import com.itextpdf.text.DocumentException;
import com.okwatch.flightreservation.dto.ReservationRequest;
import com.okwatch.flightreservation.entities.Flight;
import com.okwatch.flightreservation.entities.Reservation;
import com.okwatch.flightreservation.repos.FlightRepository;
import com.okwatch.flightreservation.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    ReservationService reservationService;

    @Autowired
    FlightRepository flightRepository;

    // GET flight details by id
    @GetMapping("/flight/{id}")
    public ResponseEntity<?> getFlightDetails(@PathVariable Long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isPresent()) {
            return ResponseEntity.ok(optionalFlight.get());
        } else {
            logger.error("Flight with ID " + id + " not found.");
            return ResponseEntity.notFound().build();
        }
    }

    // POST to create reservation
    @PostMapping("/complete")
    public ResponseEntity<?> completeReservation(@RequestBody ReservationRequest request) {
        try {
            Reservation reservation = reservationService.bookFlight(request);
            return ResponseEntity.ok(reservation);
        } catch (DocumentException | FileNotFoundException e) {
            logger.error("Error generating document: " + e.getMessage());
            return ResponseEntity.status(500).body("An error occurred while generating the reservation document.");
        } catch (Exception e) {
            logger.error("Error completing reservation: " + e.getMessage());
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }
}
