package com.kataproject.bokareva.withBoot.service;


import com.kataproject.bokareva.withBoot.model.User;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(long id);

    void save(User user);


    void updateUser(long id, @Valid User updatedUser);

    void removeUser(long id);
}
