package ru.practicum.shareit.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<User> create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<User> findAll() {
        return userService.findAll();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> findById(@RequestHeader("X-Sharer-User-Id") Long id) {
        return userService.findById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> update(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody User user) {
        userService.delete(user);
    }
}
