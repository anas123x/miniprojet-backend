package tn.esprit.com.foyer.services;

import tn.esprit.com.foyer.entities.User;

import java.util.List;

public interface IUserService {
    List<User> retrieveAllUsers();

    User addUser(User user);

    User updateUser(User user);

    User retrieveUserByEmail(String email);

    void removeUser(String email);
}