package com.vtcmer.app.model.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.vtcmer.app.model.entities.User;
import com.vtcmer.app.model.repositories.UserRepository;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {
	
	private static final AtomicLong counter = new AtomicLong();
    final static List<User> users = new ArrayList<User>();
    static {
        initialize();
    }

    @Override
    public List<User> findAllUsers() {

        return users;
    }

    private static void initialize() {
        users.add(new User(counter.incrementAndGet(), "Pepe", 30, 70000));
        users.add(new User(counter.incrementAndGet(), "Juan", 40, 50000));
        users.add(new User(counter.incrementAndGet(), "Carlos", 45, 30000));
        users.add(new User(counter.incrementAndGet(), "Silvia", 50, 40000));
    }

    @Override
    public User findById(final long id) {
        User user = null;

        for (final User us : users) {
            if (us.getId() == id) {
                user = us;
                break;
            }
        }

        return user;
    }

    @Override
    public User findByName(final String name) {

        User user = null;

        for (final User us : users) {
            if (us.getName().equals(name)) {
                user = us;
                break;
            }
        }

        return user;
    }

    @Override
    public void saveUser(final User user) {
        user.setId(counter.incrementAndGet());
        users.add(user);

    }

    @Override
    public void updateUser(final User user) {
        final int index = users.indexOf(user);
        users.add(index, user);

    }

    @Override
    public void deleteUserById(final long id) {
        users.remove(id);

    }

    @Override
    public void deleteAllUsers() {
        users.clear();

    }

    @Override
    public boolean isUserExist(final User user) {
        boolean exist = false;
        final User u = this.findByName(user.getName());
        if (u != null) {
            exist = true;
        }
        return exist;
    }


}
