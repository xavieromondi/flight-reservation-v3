package com.okwatch.flightreservation.repos;

import com.okwatch.flightreservation.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
