package org.example.service.customServices;


import org.example.repository.PublisherRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomPublisherDetailsService {

    private PublisherRepository publisherRepository;
    public CustomPublisherDetailsService(PublisherRepository publisherRepository){this.publisherRepository=publisherRepository;}
}
