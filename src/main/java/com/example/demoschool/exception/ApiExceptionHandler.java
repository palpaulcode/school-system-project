/*
package com.example.demoschool.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

*/
/**
 * anotation @ControllerAdvice tells spring this class is used for
 * handling multiple exceptions
 *//*

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    */
/**
     * annotation @ExceptionHandler tell spring that this method is
     * responsible for handling exception specified in method parameter
     *//*

    @ExceptionHandler(value = {ApiRequestException.class,
            ApiIdMismatchException.class, ConstraintViolationException.class,
            DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        // 1. Create a pay load containing exception details
        */
/**
         * variable 'apiException' contains the actual payload information
         * *//*

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(e.getMessage(), badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        //2. Return response entity
        return new ResponseEntity<>(apiException, badRequest);

    }
}
*/
