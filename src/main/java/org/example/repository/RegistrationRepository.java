package org.example.repository;

import org.example.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration,Long> {

    Registration findById(String id);


}
