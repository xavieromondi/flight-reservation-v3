package com.okwatch.flightreservation.repos;

import com.okwatch.flightreservation.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query(value = "SELECT * FROM Flight WHERE departureCity = :from AND arrivalCity = :to And dateOfDeparture =:departureDate", nativeQuery = true)
    List<Flight> findFlights(@Param("from") String from,@Param("to") String to,@Param("departureDate") String departureDate);


}
