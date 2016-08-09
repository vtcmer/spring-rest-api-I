package com.vtcmer.app.model.services;

import java.util.List;

import com.vtcmer.app.model.entities.User;

public interface UserService {
	
	User findById(long id);

    User findByName(String name);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(long id);

    List<User> findAllUsers();

    void deleteAllUsers();

    public boolean isUserExist(User user);

}
