package com.okwatch.flightreservation.services;

import com.itextpdf.text.DocumentException;
import com.okwatch.flightreservation.dto.ReservationRequest;
import com.okwatch.flightreservation.entities.Flight;
import com.okwatch.flightreservation.entities.Passenger;
import com.okwatch.flightreservation.entities.Reservation;
import com.okwatch.flightreservation.repos.FlightRepository;
import com.okwatch.flightreservation.repos.PassengerRepository;
import com.okwatch.flightreservation.repos.ReservationRepository;
import com.okwatch.flightreservation.util.EmailUtil;
import com.okwatch.flightreservation.util.PdfGenarator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    PdfGenarator pdfGenarator;

    @Autowired
    EmailUtil emailUtil;

    @Override
    public Reservation bookFlight(ReservationRequest request) throws DocumentException, FileNotFoundException {
        Long flightId = request.getFlightId();
        Optional<Flight> flight = flightRepository.findById(flightId);

        if (flight.isPresent()) {
            Flight flight1 = flight.get();

            Passenger passenger = new Passenger();
            passenger.setFirstName(request.getPassengerFirstName());
            passenger.setLastName(request.getPassengerLastName());
            passenger.setPhone(request.getPassengerPhone());
            passenger.setEmail(request.getPassengerEmail());
            Passenger savedPassenger = passengerRepository.save(passenger);

            Reservation reservation = new Reservation();
            reservation.setFlight(flight1);
            reservation.setPassenger(savedPassenger);
            reservation.setCheckedIn(false);

            Reservation savedReservation = reservationRepository.save(reservation);

            String tempDir = System.getProperty("java.io.tmpdir");
            String filePath = tempDir + "/reservation_" + savedReservation.getId() + ".pdf";


            pdfGenarator.generateItenerary(savedReservation, filePath);
            emailUtil.sendItinerary(passenger.getEmail(), filePath);

            return savedReservation;
        } else {
            throw new RuntimeException("Flight with ID " + flightId + " not found.");
        }
    }
}
