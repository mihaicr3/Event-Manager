package org.example.service;

import org.example.dto.EventDto;
import org.example.entity.Event;

import java.util.List;

public interface EventService {

    void saveEvent(EventDto eventDto);

    Event findById(int id);

    Event findByName(String name);
    List<Event> findAllEvents();
}
