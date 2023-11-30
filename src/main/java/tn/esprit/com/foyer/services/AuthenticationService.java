package tn.esprit.com.foyer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entities.*;
import tn.esprit.com.foyer.repositories.EtudiantRepository;
import tn.esprit.com.foyer.requests.AuthenticationRequest;
import tn.esprit.com.foyer.requests.AuthenticationResponse;
import tn.esprit.com.foyer.requests.RegisterRequest;
import tn.esprit.com.foyer.config.JwtService;
import tn.esprit.com.foyer.repositories.TokenRepository;
import tn.esprit.com.foyer.repositories.UserRepository;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final EtudiantRepository etudiantRepository;

  public ResponseEntity register(RegisterRequest request) {
    /*Etudiant etudiant = etudiantRepository.findByEmailEtudiant(request.getEmail());
    if (etudiant == null) {
      // Return error if email already exists
      return ResponseEntity.status(HttpStatus.CONFLICT)
              .body(AuthenticationResponse.builder()
                      .error("Email n'appartient a aucun etudiant")
                      .build());
    }*/
    User user1 = repository.findByEmail(request.getEmail());
    if (user1 != null) {
      // Return error if email already exists
      return ResponseEntity.status(HttpStatus.CONFLICT)
              .body(AuthenticationResponse.builder()
                      .error("Email already exists")
                      .build());
    }

    var user = User.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    //saveUserToken(savedUser, jwtToken);
    return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body(AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .build());

  }

  public ResponseEntity authenticate(AuthenticationRequest request) {


    AuthenticationResponse response;

    User user = repository.findByEmail(request.getEmail());
    if (user == null) {
      // Return error if email doesn't exist
      return ResponseEntity.status(HttpStatus.CONFLICT)
              .body(AuthenticationResponse.builder()
                      .error("Email not found")
                      .build());
    }

    try {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getEmail(),
                      request.getPassword()
              )
      );

      var jwtToken = jwtService.generateToken(user);
      //revokeAllUserTokens(user);
      saveUserToken(user, jwtToken);

      // Extracting claims as JSON
      String claimsJson = jwtService.extractClaim(jwtToken, claims -> {
        // Convert claims to JSON here using a library like Jackson or Gson
        // For example using Jackson:
        ObjectMapper objectMapper = new ObjectMapper();
        try {
          return objectMapper.writeValueAsString(claims);
        } catch (JsonProcessingException e) {
          // Handle JSON processing exception
          e.printStackTrace();
          return null;
        }
      });

      response = AuthenticationResponse.builder()
              .accessToken(jwtToken)
              .build();
    } catch (BadCredentialsException e) {
      // Handle incorrect password
      return ResponseEntity.status(HttpStatus.CONFLICT)
              .body(AuthenticationResponse.builder()
                      .error("Incorrect Password")
                      .build());
    }

    return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body(response);
  }





  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      User user = this.repository.findByEmail(userEmail);

      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
