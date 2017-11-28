package com.prashrajan.taxicabservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class CarAlreadyInUseException extends Exception
{

    private static final long serialVersionUID = 3859780152447089226L;


    public CarAlreadyInUseException(String message)
    {
        super(message);
    }
}
