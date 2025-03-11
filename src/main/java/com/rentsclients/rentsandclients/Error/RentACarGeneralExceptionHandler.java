package com.rentsclients.rentsandclients.Error;

import com.rentsclients.rentsandclients.Exceptions.CarNotFoundException;
import com.rentsclients.rentsandclients.Exceptions.ClientNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RentAcarExceptionHandler {
    @ExceptionHandler(value = {
            CarNotFoundException.class,
            ClientNotFoundException.class})

    public ResponseEntity<Error> handleNotFoundException(Exception exception, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.builder()
                .timestamp(new Date())
                .msg(exception.getMessage())
                .method(request.getMethod())
                .path(request.getRequestURI())

                .build());
    }
}
