package tn.esprit.com.foyer.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.com.foyer.requests.AuthenticationRequest;
import tn.esprit.com.foyer.requests.AuthenticationResponse;
import tn.esprit.com.foyer.requests.RegisterRequest;
import tn.esprit.com.foyer.services.AuthenticationService;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity register(
      @RequestBody RegisterRequest request
  ) {
    return service.register(request);
  }
  @PostMapping("/authenticate")
  public ResponseEntity authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return service.authenticate(request);
  }




}
