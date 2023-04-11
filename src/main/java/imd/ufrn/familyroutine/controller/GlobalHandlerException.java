package imd.ufrn.familyroutine.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import imd.ufrn.familyroutine.model.RestResponse;
import imd.ufrn.familyroutine.service.exception.EntityNotFoundException;

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

}
