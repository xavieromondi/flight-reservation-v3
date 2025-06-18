package com.okwatch.flightreservation.entities;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private Boolean checkedIn;
    private Integer numberOfBags;
    @OneToOne
    private Passenger passenger;
    @OneToOne
    private Flight flight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public Integer getNumberOfBags() {
        return numberOfBags;
    }

    public void setNumberOfBags(Integer numberOfBags) {
        this.numberOfBags = numberOfBags;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", checkedIn=" + checkedIn +
                ", numberOfBags=" + numberOfBags +
                ", passenger=" + passenger +
                ", flight=" + flight +
                '}';
    }
}
