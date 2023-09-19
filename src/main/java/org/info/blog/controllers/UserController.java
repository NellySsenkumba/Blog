package org.info.blog.controllers;

import org.info.blog.models.User;
import org.info.blog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<List<User>> addUser(@RequestBody List<User> user) {
        return userService.addUser(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> allUsers() {
        return userService.allUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> oneUsers(@PathVariable long id) {
        return userService.oneUsers(id);
    }

    @PatchMapping
    public ResponseEntity<User> editUsers(@RequestBody User user) {
        return userService.editUsers(user);
    }
}
