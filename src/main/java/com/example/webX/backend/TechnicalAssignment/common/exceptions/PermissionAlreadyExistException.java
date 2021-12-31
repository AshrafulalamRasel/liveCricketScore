package com.example.webX.backend.TechnicalAssignment.common.exceptions;

public class PermissionAlreadyExistException extends RuntimeException {
    public PermissionAlreadyExistException(String message) {
        super(message);
    }
}
