package org.example.service;

import org.example.dto.RegistrationDto;
import org.example.dto.UserDto;
import org.example.entity.Registration;
import org.example.entity.User;

import java.util.List;

public interface RegistrationService {
    void saveRegistration(RegistrationDto registrationDto);

    Registration findById(String id);



    List<Registration> findAllRegistrations();
}
