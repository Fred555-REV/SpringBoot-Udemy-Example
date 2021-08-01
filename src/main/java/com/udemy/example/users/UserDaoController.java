package com.udemy.example.users;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
        return this.userDaoService.getUsers();
    }

    @GetMapping(path = "users/{id}")
    public EntityModel<User> getUser(@PathVariable Integer id) {

        User user = this.userDaoService.getUser(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUser = linkTo(methodOn(this.getClass()).getUsers());
        model.add(linkToUser.withRel("all-users"));
        return model;
    }

    @DeleteMapping(path = "users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        User deletedUser = this.userDaoService.deleteUser(id);

        if (deletedUser == null) {
            throw new UserNotFoundException("id-" + id);
        }
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
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User newUser = this.userDaoService.createUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getID()).toUri();
        return ResponseEntity.created(location).build();
    }
}
