package com.example.webX.backend.TechnicalAssignment.auth.web.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private String token;
    private LoggedUserDetailsResponse authUserDetails;


    private String type = "Bearer";

    public JwtResponse(String accessToken,LoggedUserDetailsResponse authUserInfo) {
        this.token = accessToken;
        this.authUserDetails = authUserInfo;
    }



}
