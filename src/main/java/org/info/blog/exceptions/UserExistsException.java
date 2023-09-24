package org.info.blog.exceptions;

public class UserExistsException extends RuntimeException{
    public UserExistsException() {
        super("User Already Exists");
    }
}
