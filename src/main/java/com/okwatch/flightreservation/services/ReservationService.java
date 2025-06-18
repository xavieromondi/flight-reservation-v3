package com.okwatch.flightreservation.services;

import com.itextpdf.text.DocumentException;
import com.okwatch.flightreservation.dto.ReservationRequest;
import com.okwatch.flightreservation.entities.Reservation;

import java.io.FileNotFoundException;

public interface ReservationService {
    public Reservation bookFlight(ReservationRequest request) throws DocumentException, FileNotFoundException;
}
