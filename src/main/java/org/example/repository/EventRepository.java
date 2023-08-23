package org.example.repository;

import org.example.dto.EventDto;
import org.example.entity.Event;
import org.example.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, List> {

    Event findById(int id);
    Event findByName(String name);
}
