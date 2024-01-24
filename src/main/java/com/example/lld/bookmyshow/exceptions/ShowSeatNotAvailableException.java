package com.example.lld.bookmyshow.exceptions;


public class ShowSeatNotAvailableException extends Exception {
    public ShowSeatNotAvailableException(){
        super("One of the selected seat not available");
    }
}
