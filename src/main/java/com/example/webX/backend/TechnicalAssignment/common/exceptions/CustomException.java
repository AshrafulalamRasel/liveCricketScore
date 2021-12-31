package com.example.webX.backend.TechnicalAssignment.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomException {
    /**
     *
     * For Resource Check
     * @param ex
     * @param request
     * @return Resource
     */
    @ExceptionHandler(PermissionAlreadyExistException.class)
    public final ResponseEntity<ExceptionMessage> permissionAlreadyExistException(PermissionAlreadyExistException ex, WebRequest request) {
        ExceptionMessage error = new ExceptionMessage(HttpStatus.CONFLICT, ex.getMessage());
        System.err.println(error.toString());
        return new ResponseEntity<ExceptionMessage>(error, HttpStatus.CONFLICT);
    }

    /**
     * For emailAlreadyExistException
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(EmailAlreadyExistException.class)
    public final ResponseEntity<ExceptionMessage> emailAlreadyExistException(EmailAlreadyExistException ex, WebRequest request) {
        ExceptionMessage error = new ExceptionMessage(HttpStatus.CONFLICT, ex.getMessage());
        System.err.println(error.toString());
        return new ResponseEntity<ExceptionMessage>(error, HttpStatus.CONFLICT);
    }

    /**
     * For RoleAlreadyExistException
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(RoleAlreadyExistException.class)
    public final ResponseEntity<ExceptionMessage> roleAlreadyExistException(RoleAlreadyExistException ex, WebRequest request) {
        ExceptionMessage error = new ExceptionMessage(HttpStatus.CONFLICT, ex.getMessage());
        System.err.println(error.toString());
        return new ResponseEntity<ExceptionMessage>(error, HttpStatus.CONFLICT);
    }

    /**
     * For Role NotFound Exception
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(RoleNotFoundException.class)
    public final ResponseEntity<ExceptionMessage> roleAlreadyExistException(RoleNotFoundException ex, WebRequest request) {
        ExceptionMessage error = new ExceptionMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        System.err.println(error.toString());
        return new ResponseEntity<ExceptionMessage>(error, HttpStatus.NOT_FOUND);
    }
}
