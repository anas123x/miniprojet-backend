package tn.esprit.com.foyer.dto;

import tn.esprit.com.foyer.entities.User;

import java.util.Optional;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    // Other fields as needed

    public UserDTO() {
        // Default constructor
    }

    public UserDTO(String firstName, String lastName, String email, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        // Initialize other fields as needed
    }
    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setFirstName(user.getFirstname());
        dto.setLastName(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().toString());
        // Set other fields as needed
        return dto;
    }
    // Getter and Setter methods for firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter and Setter methods for lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter and Setter methods for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter methods for role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Other getters and setters for additional fields
}
