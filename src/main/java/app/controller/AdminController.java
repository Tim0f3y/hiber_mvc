package app.controller;

import app.entity.Role;
import app.entity.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(ModelMap modelMap, @AuthenticationPrincipal User user){
        List<User> list = service.getAllUsers();
        modelMap.addAttribute("list", list);
        modelMap.addAttribute("user", user);
        return "index";
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("users") User user, @RequestParam("option") String role) {
        Set<Role> roleSet = new LinkedHashSet<>();
        Role role_admin = service.getByName("ROLE_ADMIN");
        Role role_user = service.getByName("ROLE_USER");
        if (!role.contains(",")) {
            roleSet.add(service.getByName(role));
        }
        else{
            roleSet.add(role_admin);
            roleSet.add(role_user);
        }
        user.setRole(roleSet);
        service.addUser(user);

        return "redirect:/admin/";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("users") User user, @RequestParam("option") String role) {
        Set<Role> roleSet = new LinkedHashSet<>();
        Role role_admin = service.getByName("ROLE_ADMIN");
        Role role_user = service.getByName("ROLE_USER");
        if (!role.contains(",")) {
            roleSet.add(service.getByName(role));
        }
        else{
            roleSet.add(role_admin);
            roleSet.add(role_user);
        }

        user.setRole(roleSet);
        service.update(user);
        return "redirect:/admin/";
    }

    @RequestMapping("delete")
    public String deleteUser(@RequestParam Long id) {
        service.deleteUser(id);
        return "redirect:/admin/";
    }

}
