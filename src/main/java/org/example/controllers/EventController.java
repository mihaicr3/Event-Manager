package org.example.controllers;



import org.example.entity.Registration;
import org.example.repository.RegistrationRepository;
import org.json.JSONArray;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.example.dto.EventDto;
import org.example.entity.Event;
import org.example.entity.Publisher;
import org.example.repository.EventRepository;
import org.example.service.EventService;
import org.example.service.PublisherService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private EventService eventService;
    @Autowired
    private RegistrationRepository registrationRepository;

    @PostMapping("/publisher/publish")
    public String publishEvent(@RequestBody EventDto eventdto, @RequestHeader("Authorization") String jwt) {
       // header.getValues();

        Claims jwtValues= Jwts.parser().parseClaimsJwt(jwt).getBody();
        String jwtString= jwtValues.toString();
        String email=jwtString.substring(7,jwtString.indexOf(','));
        String password=jwtString.substring(jwtString.indexOf(",")+11,jwtString.length()-1);

        Publisher publisher=new Publisher();
        publisher=publisherService.findByEmail(email);

        String publisherPassword=publisher.getPassword();
        if(publisherPassword.equals(password))
        {

            eventdto.setPublisher_id(publisher.getId());


            eventService.saveEvent(eventdto);

            Event event=eventService.findByName(eventdto.getName());

            org.json.JSONObject json= new JSONObject();
            json.put("id",event.getId().toString());
            return json.toString();
        }

        return ResponseEntity.badRequest().toString();

    }

    @GetMapping("/event/{eventId}")
    public String getEventDetails(@PathVariable("eventId") int eventId,@RequestHeader("Authorization") String jwt)
    {
        Claims jwtValues = Jwts.parser().parseClaimsJwt(jwt).getBody();
        String jwtString = jwtValues.toString();
        String email = jwtString.substring(7, jwtString.indexOf(','));
        String password = jwtString.substring(jwtString.indexOf(",") + 11, jwtString.length() - 1);

        Publisher publisher = new Publisher();
        publisher = publisherService.findByEmail(email);

        String publisherPassword = publisher.getPassword();
        if (publisherPassword.equals(password)) {
            Event event = eventService.findById(eventId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", event.getId());
            jsonObject.put("name", event.getName());
            jsonObject.put("category", event.getCategory());
            jsonObject.put("description", event.getDescription());
            jsonObject.put("date_from", event.getDate_from());
            jsonObject.put("date_to", event.getDate_to());
            jsonObject.put("location", event.getLocation());
            jsonObject.put("publisher_id", event.getPublisher_id());

            return jsonObject.toString();
        }
        return ResponseEntity.notFound().toString();
    }

    @PostMapping("/user/list")
    public String listEvents(@RequestBody String data,@RequestHeader("Authorization") String jwt) {
        Claims jwtValues = Jwts.parser().parseClaimsJwt(jwt).getBody();
        String jwtString = jwtValues.toString();
        String email = jwtString.substring(7, jwtString.indexOf(','));
        String password = jwtString.substring(jwtString.indexOf(",") + 11, jwtString.length() - 1);

        Publisher publisher = new Publisher();
        publisher = publisherService.findByEmail(email);

        String publisherPassword = publisher.getPassword();
        if (publisherPassword.equals(password)) {
            JSONObject jsonObject = new JSONObject(data);
            //return jsonObject.getString("radius");
//        JSONArray jsonArray=new JSONArray(eventService.findAllEvents());
//        return jsonArray.getJSONObject(0).toString();


            List<Event> eventList = eventService.findAllEvents();
            List<Event> eventList1 = eventList;
            for (int i = 0; i < eventList.size(); i++) {
                if (!jsonObject.getString("date_from").equals("")) {

                    String dateFromString = jsonObject.getString("date_from");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateFrom = LocalDate.parse(dateFromString, formatter);
                    if (eventList1.get(i).getDate_from().toLocalDate().isBefore(dateFrom)) {
                        System.out.println(eventList.get(i).toString());
                        eventList1.remove(i);
                        i = i - 1;
                        continue;
                    }
                }
                if (!jsonObject.getString("date_to").equals("")) {

                    String dateToString = jsonObject.getString("date_to");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateTo = LocalDate.parse(dateToString, formatter);
                    if (eventList1.get(i).getDate_to().toLocalDate().isAfter(dateTo)) {
                        System.out.println(eventList.get(i).toString());
                        eventList1.remove(i);
                        i = i - 1;
                        continue;
                    }
                }
                if (!jsonObject.getString("location").equals("")) {
                    if (!eventList1.get(i).getLocation().equals(jsonObject.getString("location"))) {
                        System.out.println(eventList.get(i).toString());
                        eventList1.remove(i);
                        i = i - 1;
                        continue;
                    }
                }
                if (!jsonObject.getString("category").equals("")) {
                    if (!eventList1.get(i).getCategory().equals(jsonObject.getString("category"))) {
                        System.out.println(eventList.get(i).toString());
                        eventList1.remove(i);
                        i = i - 1;
                        continue;
                    }
                }


            }
            JSONArray jsonArray = new JSONArray();
            for (Event event : eventList1) {
//            System.out.println(event.getId());
//            System.out.println(event.getName());
//            System.out.println(event.getDescription());
//            System.out.println(event.getLocation());
//            System.out.println(event.getDate_from());
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("id", event.getId());
                jsonObject1.put("name", event.getName());
                jsonObject1.put("description", event.getDescription());
                jsonObject1.put("location", event.getLocation());
                jsonObject1.put("date_from", event.getDate_from());
                jsonArray.put(jsonObject1);
            }
            return jsonArray.toString();

        }
        else
             return ResponseEntity.notFound().toString();
    }

    @GetMapping("/event/registrations/{event_id}")
    public String listUsersOfSpecifiedEvent(@PathVariable("event_id") String eventId,@RequestHeader("Authorization") String jwt)
    {
        Claims jwtValues = Jwts.parser().parseClaimsJwt(jwt).getBody();
        String jwtString = jwtValues.toString();
        String email = jwtString.substring(7, jwtString.indexOf(','));
        String password = jwtString.substring(jwtString.indexOf(",") + 11, jwtString.length() - 1);

        Publisher publisher = new Publisher();
        publisher = publisherService.findByEmail(email);

        String publisherPassword = publisher.getPassword();
        if (publisherPassword.equals(password)) {
            List<Registration> registrations=registrationRepository.findAll();
            JSONArray jsonArray=new JSONArray();

            for(int i=0;i<registrations.size();i++)
            {
                if(registrations.get(i).getEvent_id().equals(eventId))
                {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("user_id",registrations.get(i).getUser_id());
                    jsonObject.put("event_id",registrations.get(i).getEvent_id());
                    jsonArray.put(jsonObject);

                }
            }

            return jsonArray.toString();




        }
        return ResponseEntity.notFound().toString();

    }
}
