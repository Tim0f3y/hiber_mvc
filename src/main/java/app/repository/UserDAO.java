package app.repository;

import app.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    List<User> getAllUsers();
    void deleteUser(Long id);
    User get(Long id);
    void update(User user);

    UserDetails getUserByName(String name);
}
