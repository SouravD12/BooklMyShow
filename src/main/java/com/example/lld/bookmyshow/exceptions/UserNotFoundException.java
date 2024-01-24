package com.example.lld.bookmyshow.exceptions;


public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("User is not found");
    }
}
