package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException() {
        super("No data found");
    }
}
