package com.example.webX.backend.TechnicalAssignment.auth.services;



import com.example.webX.backend.TechnicalAssignment.auth.configurations.jwt.JwtProvider;
import com.example.webX.backend.TechnicalAssignment.auth.constants.ApiResponse;
import com.example.webX.backend.TechnicalAssignment.auth.model.domain.Role;
import com.example.webX.backend.TechnicalAssignment.auth.model.domain.RoleName;
import com.example.webX.backend.TechnicalAssignment.auth.model.domain.User;
import com.example.webX.backend.TechnicalAssignment.auth.model.repositories.RoleRepository;
import com.example.webX.backend.TechnicalAssignment.auth.model.repositories.UserRepository;
import com.example.webX.backend.TechnicalAssignment.auth.web.dto.request.LoginForm;
import com.example.webX.backend.TechnicalAssignment.auth.web.dto.request.SignUpForm;
import com.example.webX.backend.TechnicalAssignment.auth.web.dto.response.JwtResponse;
import com.example.webX.backend.TechnicalAssignment.auth.web.dto.response.LoggedUserDetailsResponse;
import com.example.webX.backend.TechnicalAssignment.common.Utils.AuthLoggedUser;
import com.example.webX.backend.TechnicalAssignment.common.Utils.RandomIdUtil;
import com.example.webX.backend.TechnicalAssignment.common.exceptions.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SignUpAndSignInService {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RandomIdUtil randomIdUtil;


     public ResponseEntity<ApiResponse<String>> signUp(SignUpForm signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getEmail())) {


            return new ResponseEntity(new ApiResponse(400,"Fail -> Username is already taken!", signUpRequest.getEmail()), HttpStatus.BAD_REQUEST);

        }


        User user = new User();
        Set<RoleName> strRoles = new HashSet<>();
        strRoles.add(RoleName.SYSTEM_USER);
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case SYSTEM_USER:
                    Role adminRole = roleRepository.findByName(RoleName.SYSTEM_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                default:
                    throw new RoleNotFoundException("Role not found");
            }
        });

        String randomId = randomIdUtil.getUuid();

        user.setId(randomId);
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        user.setRoles(roles);
        userRepository.save(user);

         return new ResponseEntity(new ApiResponse(201,"Success", signUpRequest.getEmail()), HttpStatus.CREATED);

     }

    public JwtResponse signIn(LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);

        LoggedUserDetailsResponse authUserInfo = getLoggedUserDetails(authentication);
        Optional<String> loggedInAuthUserId = userRepository.findAuthIdByUserName(authentication.getName());
        System.out.println("Auth User Id" + loggedInAuthUserId);
        return new JwtResponse(jwt, authUserInfo);
    }

    public LoggedUserDetailsResponse getLoggedUserDetails(Authentication authentication) {
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        List<String> userRoleList = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            userRoleList.add(grantedAuthority.getAuthority());
        }
        LoggedUserDetailsResponse loggedUserDetailsResponse = new LoggedUserDetailsResponse();
        loggedUserDetailsResponse.setUserName(authentication.getName());
        loggedUserDetailsResponse.setUserRole(userRoleList);
        loggedUserDetailsResponse.setIsAuthenticated(authentication.isAuthenticated());
        return loggedUserDetailsResponse;
    }

}
