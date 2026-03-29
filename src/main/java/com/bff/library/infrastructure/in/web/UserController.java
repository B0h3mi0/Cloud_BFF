package com.bff.library.infrastructure.in.web;

import com.bff.library.application.port.in.UserUseCase;
import com.bff.library.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userUseCase.createUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return userUseCase.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userUseCase.getAllUsers();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userUseCase.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public Void deleteUser(@PathVariable String id) {
        return userUseCase.deleteUser(id);
    }
}
