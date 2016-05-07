package com.entreprise.azeredudu.controller;

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

import com.entreprise.azeredudu.model.User;
import com.entreprise.azeredudu.service.UserService;

@RestController
public class HelloWorldController {
    @Autowired
    private UserService userService;

    @RequestMapping( value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<User>> findAllUsers() {
        System.out.println( "Fetching users" );
        List<User> users = userService.findAllUSers();
        if ( users.isEmpty() ) {
            System.out.println( "No user found" );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        }
        return new ResponseEntity<List<User>>( users, HttpStatus.OK );
    }

    @RequestMapping( value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<User> findUserById( @PathVariable( "id" ) long id ) {
        System.out.println( "Fetching user with id: " + id );
        User user = userService.findById( id );
        if ( user == null ) {
            System.out.println( "No user found with id: " + id );
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<User>( user, HttpStatus.OK );

    }

    @RequestMapping( value = "/user/{id}", method = RequestMethod.DELETE )
    public ResponseEntity<User> deleteUserById( @PathVariable( "id" ) long id ) {
        System.out.println( "Deleting user with id: " + id );
        User user = userService.findById( id );
        if ( user == null ) {
            System.out.println( "No user found with id: " + id );
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
        userService.deleteUSerByID( id );
        return new ResponseEntity<User>( user, HttpStatus.OK );

    }

    @RequestMapping( value = "/user", method = RequestMethod.DELETE )
    public ResponseEntity<User> deleteAllUsers() {
        System.out.println( "Deleting users" );
        userService.deleteAllUsers();
        return new ResponseEntity<>( HttpStatus.OK );

    }

    @RequestMapping( value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Void> saveUser( @RequestBody User user, UriComponentsBuilder uriComponentsBuilder ) {
        System.out.println( "Saving user with username: " + user.getUsername() );
        if ( userService.isUserExist( user ) ) {
            System.out.println( "A user with username already exist!" );
            return new ResponseEntity<Void>( HttpStatus.CONFLICT );
        }
        userService.saveUSer( user );
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation( uriComponentsBuilder.path( "/user/{id}" ).buildAndExpand( user.getId() ).toUri() );
        return new ResponseEntity<Void>( headers, HttpStatus.CREATED );
    }

    @RequestMapping( value = "/user/{id}", method = RequestMethod.PUT )
    public ResponseEntity<User> updateUser( @PathVariable( "id" ) long id, @RequestBody User user ) {
        System.out.println( "Fecthing and updating user id: " + id );
        User currentUser = userService.findById( id );
        if ( currentUser == null ) {
            System.out.println( "Couldn't update, not user found with " + id );
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
        currentUser.setAddress( user.getAddress() );
        currentUser.setEmail( user.getEmail() );
        currentUser.setUsername( user.getUsername() );
        userService.updateUser( currentUser );
        return new ResponseEntity<User>( currentUser, HttpStatus.OK );
    }

}
