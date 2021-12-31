package com.example.webX.backend.TechnicalAssignment.auth.web.controller;


import com.example.webX.backend.TechnicalAssignment.auth.constants.ApiResponse;
import com.example.webX.backend.TechnicalAssignment.auth.constants.LoginConstant;
import com.example.webX.backend.TechnicalAssignment.auth.services.SignUpAndSignInService;
import com.example.webX.backend.TechnicalAssignment.auth.web.dto.request.LoginForm;
import com.example.webX.backend.TechnicalAssignment.auth.web.dto.request.SignUpForm;
import com.example.webX.backend.TechnicalAssignment.common.constants.AccessApiConstant;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping(AccessApiConstant.PUBLIC)
public class AuthController {

    private final SignUpAndSignInService signUpAndSignInService;

    @PostMapping(LoginConstant.LOGIN)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {

        return ResponseEntity.ok(signUpAndSignInService.signIn(loginRequest));
    }

    @PostMapping(LoginConstant.SIGNUP)
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody SignUpForm signUpRequest) {
        return new ResponseEntity(signUpAndSignInService.signUp(signUpRequest), HttpStatus.CREATED);
    }

}
