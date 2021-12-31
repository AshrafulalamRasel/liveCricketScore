package com.example.webX.backend.TechnicalAssignment.common.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Setter
@Getter

public class ExceptionMessage {
    Date date;
    HttpStatus message;
    String details;

    public ExceptionMessage(HttpStatus message, String details) {
        this.message = message;
        this.details = details;
        date = new Date();
    }

    @Override
    public String toString() {
        return "ExceptionMessage{" +
                "date=" + date +
                ", message=" + message +
                ", details='" + details + '\'' +
                '}';
    }
}