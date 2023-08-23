package org.example.service.customServices;

import org.example.repository.PublisherRepository;
import org.example.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomRegistrationDetailsService {

    private RegistrationRepository registrationRepository;
    public CustomRegistrationDetailsService(RegistrationRepository registrationRepository){this.registrationRepository=registrationRepository;}

}
