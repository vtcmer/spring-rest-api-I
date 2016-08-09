package com.vtcmer.app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.vtcmer.app.model.entities.User;
import com.vtcmer.app.model.services.UserService;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {
	
	@Autowired
    private UserService userService;

    /**
     * Recuparaci�n de la lista de usuario
     *
     *
     * @return
     */
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAllUsers() {

        final List<User> users = this.userService.findAllUsers();

        if (users.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);

    }

    /**
     * Recuperaci�n de un usuario
     *
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") final long id) {

        ResponseEntity<User> response = null;

        final User user = this.userService.findById(id);
        if (user != null) {
            response = new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            response = new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return response;

    }

    /**
     * Creación del usuario
     *
     *
     * @param user
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody final User user, final UriComponentsBuilder ucBuilder) {

        ResponseEntity<Void> response = null;

        final boolean existUser = this.userService.isUserExist(user);
        if (existUser) {
            response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
        } else {

            // -- se crea el usuario
            this.userService.saveUser(user);

            // -- se redirecciona a la pantalla de edici�n
            final HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
            response = new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }

        return response;

    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") final long id, @RequestBody final User user) {

        ResponseEntity<User> response = null;

        final User u = this.userService.findById(id);
        if (u == null) {
            response = new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        } else {
            this.userService.updateUser(user);
            response = new ResponseEntity<User>(user, HttpStatus.OK);
        }

        return response;

    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") final long id) {

        ResponseEntity<Void> response = null;

        final User user = this.userService.findById(id);
        if (user == null) {
            response = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            this.userService.deleteUserById(id);
            response = new ResponseEntity<Void>(HttpStatus.OK);
        }

        return response;
    }


}
