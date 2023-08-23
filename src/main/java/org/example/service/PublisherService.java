package org.example.service;

import org.example.dto.PublisherDto;
import org.example.entity.Publisher;

import java.util.List;

public interface PublisherService {

    void savePublisher(PublisherDto publisherDto);

    Publisher findById(int id);
    Publisher findByEmail(String email);

    List<PublisherDto> findAllPublishers();
}
