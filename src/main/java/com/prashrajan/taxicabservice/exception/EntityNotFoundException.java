package com.prashrajan.taxicabservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EntityNotFoundException extends Exception
{
    static final long serialVersionUID = -3387516993334229948L;
   
    public EntityNotFoundException(String message)
    {
         super(message);
    }
    
}
