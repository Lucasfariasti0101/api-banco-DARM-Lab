package com.darm.apibanco.exeption;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException() {
        super("Person not found");
    }
}
