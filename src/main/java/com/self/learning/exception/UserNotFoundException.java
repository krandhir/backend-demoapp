package com.self.learning.exception;

/**
 * The type User not found exception.
 */
public class UserNotFoundException extends Exception{

    /**
     * Instantiates a new User not found exception.
     *
     * @param id the id
     */
    public UserNotFoundException(Long id){
        super("User with id: " + id + " was not found");
    }
}
