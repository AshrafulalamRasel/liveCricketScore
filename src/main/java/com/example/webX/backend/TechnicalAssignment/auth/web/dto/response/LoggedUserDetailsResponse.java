package com.example.webX.backend.TechnicalAssignment.auth.web.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

public class LoggedUserDetailsResponse {

    private String userName;

    private List<String> userRole;

    private Boolean isAuthenticated;
}
