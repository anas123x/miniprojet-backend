package tn.esprit.com.foyer.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.dto.UserDTO;
import tn.esprit.com.foyer.requests.ChangePasswordRequest;
import tn.esprit.com.foyer.entities.User;
import tn.esprit.com.foyer.repositories.UserRepository;
import tn.esprit.com.foyer.requests.ChangePasswordResponse;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    public ChangePasswordResponse changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        ChangePasswordResponse response = new ChangePasswordResponse();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            response.setMessage("Wrong password");
            return response;
        }

        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            response.setMessage("Passwords are not the same");
            return response;
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);

        response.setMessage("Password changed successfully");
        return response;
    }
    public UserDTO getUserDataByEmail(String email) {
        User user = repository.findByEmail(email);
        // Map User entity to UserDTO if needed
        if (user != null) {
            return UserDTO.fromEntity(user); // Assuming UserDTO has a method to convert User entity to DTO
        }
        return null;
    }
}
