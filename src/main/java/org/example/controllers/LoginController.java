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
public class LoginController {
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/publisher/authenticate")
    public String loginPublisher(@RequestBody PublisherDto publisherDto, HttpServletResponse response)
    {
        Publisher publisher1=new Publisher();
        publisher1=publisherService.findByEmail(publisherDto.getEmail());
        if(passwordEncoder.matches(publisherDto.getPassword(),publisher1.getPassword())){//passwordEncoder.encode(publisherDto.getPassword())==publisher1.getPassword()) {

            org.json.JSONObject json= new JSONObject();

            String jwToken= Jwts.builder().claim("email",publisher1.getEmail()).claim("password",publisher1.getPassword()).compact();

            json.put("Authorization",jwToken);
            return json.toString();

        }
        else
            return "Wrong Credentials";
    }

    @PostMapping("/user/authenticate")
    public String  loginUser(@RequestBody UserDto userDto)
    {
        User user=new User();
        user=userService.findByEmail(userDto.getEmail());
        if(passwordEncoder.matches(userDto.getPassword(),user.getPassword())){//passwordEncoder.encode(publisherDto.getPassword())==publisher1.getPassword()) {

            org.json.JSONObject json= new JSONObject();

            String jwToken= Jwts.builder().claim("email",user.getEmail()).claim("password",user.getPassword()).compact();

            json.put("Authorization",jwToken);
            return json.toString();

        }
        else
            return "Wrong Credentials";
    }
}
