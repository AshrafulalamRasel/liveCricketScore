package com.example.webX.backend.TechnicalAssignment.common.Utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuthLoggedUser {

    public String getLoggedAuthUser() {

        Object authUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<String> loggedInAuthUserId = null;
        String loggedInAuthUser = null;

        if (authUser instanceof UserDetails) {

            String username = ((UserDetails) authUser).getUsername();
            loggedInAuthUser = username;

        } else if (authUser instanceof UserDetails == false) {
            throw new RuntimeException("LoggedIn user does not  account.");

        } else {
            String username = authUser.toString();

            System.out.println(username);
        }
        return loggedInAuthUser;

    }

}
