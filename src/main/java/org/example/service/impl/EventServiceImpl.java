package org.example.service.impl;

import org.example.dto.EventDto;
import org.example.entity.Event;
import org.example.repository.EventRepository;
import org.example.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {


    EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository)
    {
        this.eventRepository=eventRepository;
    }

    @Override
    public void saveEvent(EventDto eventDto)
    {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setPublisher_id(eventDto.getPublisher_id());
        event.setCategory(eventDto.getCategory());
        event.setDate_from(eventDto.getDate_from());
        event.setDate_to(eventDto.getDate_to());
        event.setLocation(eventDto.getLocation());
        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());

        eventRepository.save(event);
        eventRepository.flush();
    }
    private EventDto convertEntityToDto(Event event){
        EventDto eventDto = new EventDto();
        //String[] name = event.getName().split(" ");
        eventDto.setName(event.getName());
        eventDto.setDescription(event.getDescription());
        eventDto.setPublisher_id(event.getPublisher_id());
        return eventDto;
    }

    @Override
    public Event findById(int id) {
        return eventRepository.findById(id);
    }

    @Override
    public Event findByName(String name){ return  eventRepository.findByName(name);}

    @Override
    public List<Event> findAllEvents() {
        List<Event> events = eventRepository.findAll();

        return eventRepository.findAll();
//        return events.stream().map((event) -> convertEntityToDto(event))
//
//                .collect(Collectors.toList());
    }
}