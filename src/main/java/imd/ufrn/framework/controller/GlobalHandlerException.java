package imd.ufrn.framework.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import imd.ufrn.framework.model.RestResponse;
import imd.ufrn.framework.service.exception.ActivityIntervalException;
import imd.ufrn.framework.service.exception.EntityNotFoundException;
import imd.ufrn.framework.service.exception.InvalidStateException;
import imd.ufrn.framework.service.exception.RecurringActivityException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        RestResponse response = RestResponse.builder()
            .status(HttpStatus.NOT_FOUND)
            .message(ex.getMessage())
            .path(request.getDescription(false).substring(4))
            .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ ActivityIntervalException.class })
    public ResponseEntity<Object> handleActivityIntervalException(ActivityIntervalException ex, WebRequest request) {
        RestResponse response = RestResponse.builder()
            .status(HttpStatus.BAD_REQUEST)
            .message(ex.getMessage())
            .path(request.getDescription(false).substring(4))
            .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ RecurringActivityException.class })
    public ResponseEntity<Object> handleRecurringActivityException(RecurringActivityException ex, WebRequest request) {
        RestResponse response = RestResponse.builder()
            .status(HttpStatus.BAD_REQUEST)
            .message(ex.getMessage())
            .path(request.getDescription(false).substring(4))
            .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ InvalidStateException.class })
    public ResponseEntity<Object> handleInvalidStateException(InvalidStateException ex, WebRequest request) {
        RestResponse response = RestResponse.builder()
            .status(HttpStatus.BAD_REQUEST)
            .message(ex.getMessage())
            .path(request.getDescription(false).substring(4))
            .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
