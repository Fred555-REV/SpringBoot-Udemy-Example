package com.udemy.example.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping
public class UserDaoController {
    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    public UserDaoController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping(path = "users")
    public List<User> getUsers() {
        System.out.println("What is up fellow kids.");
        return this.userDaoService.getUsers();
    }

    @GetMapping(path = "users/{id}")
    public User getUser(@PathVariable Integer id) {

        User user = this.userDaoService.getUser(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        return user;
    }

    @DeleteMapping(path = "users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        this.userDaoService.deleteUser(id);
    }

    //    @PostMapping(path = "users")
//    public ResponseEntity<Object> createUser(String name, Integer year, Integer month, Integer day) {
//        Integer
//        LocalDate dob = LocalDate.of(year, month, day);
//        User newUser = this.userDaoService.createUser(name, dob);
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(newUser.getID()).toUri();
//        return ResponseEntity.created(location).build();
//    }
    @PostMapping(path = "users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User newUser = this.userDaoService.createUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getID()).toUri();
        return ResponseEntity.created(location).build();
    }
}
