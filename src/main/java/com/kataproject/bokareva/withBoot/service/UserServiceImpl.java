package com.kataproject.bokareva.withBoot.service;

import com.kataproject.bokareva.withBoot.model.User;
import com.kataproject.bokareva.withBoot.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(long id, @Valid User updatedUser) {
        updatedUser.setId( id );
        userRepository.save(updatedUser);
    }

    @Override
    public void removeUser(long id) {
        userRepository.deleteById(id);
    }
}
