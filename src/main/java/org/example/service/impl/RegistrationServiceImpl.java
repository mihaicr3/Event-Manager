package org.example.service.impl;


import org.example.dto.PublisherDto;
import org.example.dto.RegistrationDto;
import org.example.entity.Publisher;
import org.example.entity.Registration;
import org.example.repository.PublisherRepository;
import org.example.repository.RegistrationRepository;
import org.example.service.RegistrationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private RegistrationRepository registrationRepository;



    public RegistrationServiceImpl(RegistrationRepository registrationRepository

                               ) {
        this.registrationRepository = registrationRepository;

    }


    @Override
    public void saveRegistration(RegistrationDto registrationDto)
    {
       Registration registration=new Registration();

        registration.setEvent_id(registrationDto.getEvent_id());
        registration.setUser_id(registrationDto.getUser_id());


        registrationRepository.save(registration);
    }
    private RegistrationDto convertEntityToDto(Registration registration){
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEvent_id(registration.getEvent_id());
        registrationDto.setUser_id(registration.getUser_id());
        return registrationDto;
    }

    @Override
    public List<Registration> findAllRegistrations() {
        List<Registration> registrations = registrationRepository.findAll();
        return registrations;
    }
    @Override
    public Registration findById(String id) {
        return registrationRepository.findById(id);
    }




}
