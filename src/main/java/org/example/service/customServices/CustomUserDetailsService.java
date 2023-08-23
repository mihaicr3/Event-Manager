package org.example.service.customServices;


import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {
    private UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository){this.userRepository=userRepository;}
}
