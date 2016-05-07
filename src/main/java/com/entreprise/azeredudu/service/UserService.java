package com.entreprise.azeredudu.service;

import java.util.List;

import com.entreprise.azeredudu.model.User;

public interface UserService {
    User findById( long id );

    User findByUsername( String name );

    void saveUSer( User user );

    void updateUser( User user );

    void deleteUSerByID( long id );

    List<User> findAllUSers();

    void deleteAllUsers();

    public boolean isUserExist( User user );
}
