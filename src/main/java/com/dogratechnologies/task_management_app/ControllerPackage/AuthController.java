package com.dogratechnologies.task_management_app.ControllerPackage;

import com.dogratechnologies.task_management_app.LoginRequestAndResponsePackage.LoginRequest;
import com.dogratechnologies.task_management_app.LoginRequestAndResponsePackage.LoginResponse;
import com.dogratechnologies.task_management_app.Service.JwtService;
import com.dogratechnologies.task_management_app.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String>register(@RequestBody LoginRequest request)
    {
        userService.registerUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("User register Syccessfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse>login(@RequestBody LoginRequest loginRequest)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtService.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new LoginResponse(token, userDetails.getUsername()));
    }


}
