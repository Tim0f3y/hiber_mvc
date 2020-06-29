package app.service;

import app.entity.Role;
import app.entity.User;
import app.repository.RoleRepository;
import app.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleRepository roleDAO;

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void update(User user) {
      userDAO.update(user);
    }

    @Override
    public User getUser(Long id) {
        return userDAO.get(id);
    }

    @Override
    public Role getByName(String name) {
        return roleDAO.getByName(name);
    }

}
