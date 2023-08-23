package org.example.service.impl;

import org.example.dto.PublisherDto;
import org.example.entity.Publisher;
import org.example.repository.PublisherRepository;
import org.example.service.PublisherService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PublisherServiceImpl implements PublisherService {
    private PublisherRepository publisherRepository;

    private PasswordEncoder passwordEncoder;

    public PublisherServiceImpl(PublisherRepository publisherRepository,

                           PasswordEncoder passwordEncoder) {
        this.publisherRepository = publisherRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void savePublisher(PublisherDto publisherDto)
    {
        Publisher publisher = new Publisher();

        publisher.setEmail(publisherDto.getEmail());
        publisher.setDescription(publisherDto.getDescription());
        publisher.setName(publisherDto.getName());


        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        publisher.setPassword(passwordEncoder.encode(publisherDto.getPassword()));
       // Role role = roleRepository.findByName("ROLE_PUBLISHER");
//        if(role == null){
//            role = checkRoleExist();
//        }
       // publisher.set(Arrays.asList(role));
        publisherRepository.save(publisher);
    }
    private PublisherDto convertEntityToDto(Publisher publisher){
        PublisherDto publisherDto = new PublisherDto();
       // String[] name = user.getName().split(" ");
       // userDto.setFirstName(name[0]);
        //userDto.setLastName(name[1]);
        publisherDto.setEmail(publisher.getEmail());
        publisherDto.setDescription(publisher.getDescription());
        return publisherDto;
    }

    @Override
    public List<PublisherDto> findAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        return publishers.stream().map((publisher) -> convertEntityToDto(publisher))
                .collect(Collectors.toList());
    }
    @Override
    public Publisher findById(int id) {
        return publisherRepository.findById(id);
    }
    @Override
    public Publisher findByEmail(String email){ return publisherRepository.findByEmail(email);}
//    private Role checkRoleExist() {
//        Role role = new Role();
//        role.setRole_name("ROLE_ADMIN");
//        return roleRepository.save(role);
//    }
}
