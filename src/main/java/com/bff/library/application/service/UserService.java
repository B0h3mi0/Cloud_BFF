package com.bff.library.application.service;

import com.bff.library.application.port.in.UserUseCase;
import com.bff.library.application.port.out.UserFunctionPort;
import com.bff.library.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final UserFunctionPort userFunctionPort;

    @Override
    public User createUser(User user) {
        return userFunctionPort.createUser(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userFunctionPort.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userFunctionPort.getAllUsers();
    }

    @Override
    public User updateUser(String id, User user) {
        return userFunctionPort.updateUser(id, user);
    }

    @Override
    public Void deleteUser(String id) {
        return userFunctionPort.deleteUser(id);
    }
}
