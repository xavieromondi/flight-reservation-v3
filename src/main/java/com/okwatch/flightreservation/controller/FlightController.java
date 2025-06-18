package com.okwatch.flightreservation.controller;

import com.okwatch.flightreservation.entities.Flight;
import com.okwatch.flightreservation.repos.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")

public class FlightController {

    Logger logger = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private FlightRepository flightRepository;

    @PostMapping("/search")
    public List<Flight> findFlight(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String departureDate) {

        logger.info("Searching flights from: {}, to: {}, on: {}", from, to, departureDate);

        List<Flight> flights = flightRepository.findFlights(from, to, departureDate);

        if (flights.isEmpty()) {
            logger.warn("No flights found for the given criteria.");
        }

        return flights; // This will be returned as JSON
    }
}
