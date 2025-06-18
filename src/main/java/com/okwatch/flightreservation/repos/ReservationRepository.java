package com.okwatch.flightreservation.repos;

import com.okwatch.flightreservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
