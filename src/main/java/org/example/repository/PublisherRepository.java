package org.example.repository;

import org.example.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher,Long> {


    Publisher findById(int id);
    Publisher findByEmail(String email);
}
