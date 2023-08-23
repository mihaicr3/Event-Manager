package org.example.controllers;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.example.dto.EventDto;
import org.example.dto.RegistrationDto;
import org.example.entity.Publisher;
import org.example.entity.Registration;
import org.example.entity.User;
import org.example.service.RegistrationService;
import org.example.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/user/{user_id}")
    public String getUserDetails(@PathVariable("user_id") int userId,@RequestHeader("Authorization") String jwt )
    {
        Claims jwtValues = Jwts.parser().parseClaimsJwt(jwt).getBody();
        String jwtString = jwtValues.toString();
        String email = jwtString.substring(7, jwtString.indexOf(','));
        String password = jwtString.substring(jwtString.indexOf(",") + 11, jwtString.length() - 1);

        User user = new User();
        user = userService.findByEmail(email);
        String userPassword = user.getPassword();
        if (userPassword.equals(password)) {

            User user1=userService.findById(userId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", user1.getId());
            jsonObject.put("name", user1.getName());
            jsonObject.put("email", user1.getEmail());
            return jsonObject.toString();

        }
        return ResponseEntity.badRequest().toString();

    }
    @PostMapping("/user/register")
    public String registerUser(@RequestBody RegistrationDto registrationDto,@RequestHeader("Authorization") String jwt)
    {
        Claims jwtValues = Jwts.parser().parseClaimsJwt(jwt).getBody();
        String jwtString = jwtValues.toString();
        String email = jwtString.substring(7, jwtString.indexOf(','));
        String password = jwtString.substring(jwtString.indexOf(",") + 11, jwtString.length() - 1);

        User user = new User();
        user = userService.findByEmail(email);

        String userPassword = user.getPassword();
        if (userPassword.equals(password)) {

            RegistrationDto registrationDto1=new RegistrationDto();
           registrationDto1.setUser_id(user.getId().toString());

            registrationDto1.setEvent_id(registrationDto.getEvent_id().toString());
            registrationService.saveRegistration(registrationDto1);

            List<Registration> list=registrationService.findAllRegistrations();

            for (int i=0;i< list.size();i++) {


                if(list.get(i).getEvent_id().equals(registrationDto1.getEvent_id()) && list.get(i).getUser_id().equals(registrationDto1.getUser_id()))
                {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("registration_id",list.get(i).getId());
                    return jsonObject.toString();
                }

            }



        }
        return ResponseEntity.badRequest().toString();
    }
}
