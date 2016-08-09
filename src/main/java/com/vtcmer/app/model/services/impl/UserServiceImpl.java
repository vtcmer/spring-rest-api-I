package com.vtcmer.app.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtcmer.app.model.entities.User;
import com.vtcmer.app.model.repositories.UserRepository;
import com.vtcmer.app.model.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
    private UserRepository userRepository;

    @Override
    public User findById(final long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User findByName(final String name) {
        return this.userRepository.findByName(name);
    }

    @Override
    public void saveUser(final User user) {
        this.userRepository.saveUser(user);

    }

    @Override
    public void updateUser(final User user) {
        this.userRepository.updateUser(user);
    }

    @Override
    public void deleteUserById(final long id) {
        this.userRepository.deleteUserById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAllUsers();
    }

    @Override
    public void deleteAllUsers() {
        this.userRepository.deleteAllUsers();

    }

    @Override
    public boolean isUserExist(final User user) {
        return this.userRepository.isUserExist(user);
    }

}
