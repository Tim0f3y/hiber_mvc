package app.service;

import app.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    List<User> getAllUsers();
    void deleteUser(Long id);
    void update(User user);
    User getUser(Long id);
}
