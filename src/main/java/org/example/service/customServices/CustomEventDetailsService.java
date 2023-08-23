package org.example.service.customServices;

import org.example.repository.EventRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomEventDetailsService {
    private EventRepository eventRepository;
    public CustomEventDetailsService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }



}
