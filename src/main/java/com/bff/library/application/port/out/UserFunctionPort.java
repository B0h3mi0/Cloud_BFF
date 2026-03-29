package com.bff.library.application.port.out;

import com.bff.library.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserFunctionPort {
    User createUser(User user);
    Optional<User> getUserById(String id);
    List<User> getAllUsers();
    User updateUser(String id, User user);
    Void deleteUser(String id);
}
