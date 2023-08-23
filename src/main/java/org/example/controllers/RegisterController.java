package org.example.controllers;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.PublisherDto;
import org.example.dto.UserDto;
import org.example.entity.Publisher;
import org.example.entity.User;
import org.example.service.PublisherService;
import org.example.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/publisher/register")
    public String addPublisher(@RequestBody PublisherDto publisherDto, HttpServletResponse response)
    {


            publisherService.savePublisher(publisherDto);

            Publisher publisher1 = publisherService.findByEmail(publisherDto.getEmail());


            org.json.JSONObject json = new JSONObject();

            String jwToken = Jwts.builder().claim("email", publisher1.getEmail()).claim("password", publisher1.getPassword()).compact();

            json.put("Authorization", jwToken);
            return json.toString();
        }
    @PostMapping("/user/create")
    public String addUser(@RequestBody UserDto userDto)
    {
        userService.saveUser(userDto);

        User user1 = userService.findByEmail(userDto.getEmail());
        org.json.JSONObject json = new JSONObject();

        String jwToken = Jwts.builder().claim("email", user1.getEmail()).claim("password", user1.getPassword()).compact();
        json.put("Authorization",jwToken);
        return json.toString();


    }

    }



