package com.entreprise.azeredudu.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entreprise.azeredudu.model.User;

@Service( "userService" )
@Transactional
public class UserServiceImpl implements UserService {
    private static final AtomicLong counter = new AtomicLong();
    private static List<User>       users;
    static {
        users = populateDummyUser();
    }

    public static List<User> populateDummyUser() {
        List<User> users = new ArrayList<User>();
        users.add( new User( counter.incrementAndGet(), "Azere", "Jinzhou", "azeredudu1@gmail.com" ) );
        users.add( new User( counter.incrementAndGet(), "Christophe", "Pointe-Noire", "christophe@yahoo.com" ) );
        users.add( new User( counter.incrementAndGet(), "Adonnys", "Paris", "adonnyss@yahoo.fr" ) );
        return users;
    }

    public User findById( long id ) {
        // TODO Auto-generated method stub
        for ( User user : users ) {
            if ( user.getId() == id ) {
                return user;
            }
        }
        return null;
    }

    public User findByUsername( String name ) {
        // TODO Auto-generated method stub
        for ( User user : users ) {
            if ( user.getUsername().equalsIgnoreCase( name ) ) {
                return user;
            }
        }
        return null;
    }

    public void saveUSer( User user ) {
        // TODO Auto-generated method stub
        user.setId( counter.incrementAndGet() );
        users.add( user );

    }

    public void updateUser( User user ) {
        // TODO Auto-generated method stub
        Integer index = users.indexOf( user );
        users.set( index, user );

    }

    public void deleteUSerByID( long id ) {
        // TODO Auto-generated method stub

        for ( Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if ( user.getId() == id ) {
                iterator.remove();
            }
        }
    }

    public List<User> findAllUSers() {
        // TODO Auto-generated method stub
        return users;
    }

    public void deleteAllUsers() {
        // TODO Auto-generated method stub
        users.clear();

    }

    public boolean isUserExist( User user ) {
        // TODO Auto-generated method stub
        return findByUsername( user.getUsername() ) != null;
    }
}
