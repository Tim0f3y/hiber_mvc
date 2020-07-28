package app.controller;

import app.entity.Role;
import app.entity.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AdminRestController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/api/list", method = RequestMethod.GET)
    public ResponseEntity<List<User>> userList() {
        final List<User> allUsers = service.getAllUsers();
        return allUsers != null && !allUsers.isEmpty()
                ? new ResponseEntity<>(allUsers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/api/save")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        List<Role> roleSet = new ArrayList<>();

        Role role_admin = service.getByName("ROLE_ADMIN");
        Role role_user = service.getByName("ROLE_USER");
        if (user.getRole().size() > 1) {
            roleSet.add(role_admin);
            roleSet.add(role_user);
        } else {
            if (user.getRole().get(0).toString().equals("ROLE_ADMIN")) {
                roleSet.add(role_admin);
            } else {
                roleSet.add(role_user);
            }
        }

        user.setRole(roleSet);
        service.addUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/edit")
    public ResponseEntity<Void> editUser(@RequestBody User user) {
        List<Role> roleSet = new ArrayList<>();

        Role role_admin = service.getByName("ROLE_ADMIN");
        Role role_user = service.getByName("ROLE_USER");
        if (user.getRole().size() > 1) {
            roleSet.add(role_admin);
            roleSet.add(role_user);
        } else {
            if (user.getRole().get(0).toString().equals("ROLE_ADMIN")) {
                roleSet.add(role_admin);
            } else {
                roleSet.add(role_user);
            }
        }


        user.setRole(roleSet);
        service.update(user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/api/delete")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        final boolean deleted = service.deleteUser(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(value = "/api/newuser", method = RequestMethod.GET)
    public User newUser() {
        return new User();
    }


    @RequestMapping(value = "/api/all/user", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthUser(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        return currentUser != null
                ? new ResponseEntity<>(currentUser, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/api/getUserById", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserById(@RequestParam("id") Long id) {
        User userById = service.getUser(id);
        return userById != null
                ? new ResponseEntity<>(userById, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
