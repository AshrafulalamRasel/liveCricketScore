package com.example.webX.backend.TechnicalAssignment.common.exceptions;

public class RoleAlreadyExistException extends RuntimeException {
    public RoleAlreadyExistException(String message) {
        super(message);
    }
}
