package com.okwatch.flightreservation.controller;

import com.okwatch.flightreservation.dto.ReservationUpdateRequest;
import com.okwatch.flightreservation.entities.Reservation;
import com.okwatch.flightreservation.repos.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ReservationRestController {
    Logger logger = LoggerFactory.getLogger(ReservationRestController.class);
    @Autowired
    ReservationRepository reservationRepository;
@RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id){
    Optional<Reservation> optionalReservation = reservationRepository.findById(id);
    if(optionalReservation.isPresent()){
        Reservation reservation = optionalReservation.get();
        return reservation;
    }else {

        logger.error("Flight with ID " + id + " not found.");

    }
    return null;
    }
    @RequestMapping("/reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest request){
        Optional<Reservation> optionalReservation = reservationRepository.findById(request.getId());
        if(optionalReservation.isPresent()){
            Reservation reservation = optionalReservation.get();
            reservation.setNumberOfBags(request.getNumberOfBags());
            reservation.setCheckedIn(request.getCheckedIn());
            Reservation updatedReservation = reservationRepository.save(reservation);
            return updatedReservation;
        }else {

            logger.error("flight with id"+request.getId()+"not found");

        }
        return null;
    }
}
