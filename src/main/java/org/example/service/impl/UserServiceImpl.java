package org.example.service.impl;

import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
  //  private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                      //     RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
      //  this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setRole("1");
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        Role role = roleRepository.findByName("ROLE_USER");
//        if(role == null){
//            role = checkRoleExist();
//        }
//        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }



    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }
    @Override
    public User findByEmail(String email){return  userRepository.findByEmail(email);}

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
//        String[] name = user.getName().split(" ");
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

//    private Role checkRoleExist() {
//        Role role = new Role();
//        role.setRole_name("ROLE_USER");
//        return roleRepository.save(role);
//    }
}