package com.okwatch.flightreservation.repos;

import com.okwatch.flightreservation.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "roles")
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);
}
