package com.okwatch.flightreservation.repos;

import com.okwatch.flightreservation.entities.MpesaTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MpesaTransactionRepository extends JpaRepository<MpesaTransaction,String> {
}
