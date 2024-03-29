package com.delivery.service.delivery_service.security.controllers;

import com.delivery.service.delivery_service.security.dto.AuthRequest;
import com.delivery.service.delivery_service.security.dto.RegistrationRequest;
import com.delivery.service.delivery_service.security.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthRestController {

    private final AuthService service;


    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
        var token = service.loginUser(request).getToken();
        Map<String, Object> responseMessage = new HashMap<>();
        responseMessage.put("login", request.getLogin());
        responseMessage.put("token", token);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody @Valid RegistrationRequest request) {
        var token = service.createUser(request).getToken();
        Map<Object, Object> responseMessage = new HashMap<>();
        responseMessage.put("login", request.getLogin());
        responseMessage.put("token", token);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


    @PostMapping("/logout")
    public void logout(HttpServletResponse response, HttpServletRequest request) {
        SecurityContextLogoutHandler contextLogoutHandler = new SecurityContextLogoutHandler();
        contextLogoutHandler.logout(request, response, null);
    }

}
