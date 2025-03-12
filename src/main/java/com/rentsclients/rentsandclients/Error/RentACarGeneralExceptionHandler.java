package com.rentsclients.rentsandclients.Error;

import com.rentsclients.rentsandclients.Exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RentACarGeneralExceptionHandler {
    @ExceptionHandler(value = {
            CarBrandException.class,
            CarModelException.class,
            CarNotFoundException.class,
            CarPlateException.class,
            ClientFirstNameException.class,
            ClientLastNameException.class,
            ClientNotFoundException.class,
            DuplicateCarPlateException.class,
            DuplicateClientNifException.class
    })

    public ResponseEntity<Error> handleNotFoundException(Exception exception, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.builder()
                .timestamp(new Date())
                .msg(exception.getMessage())
                .method(request.getMethod())
                .path(request.getRequestURI())
                .build());
    }
}
