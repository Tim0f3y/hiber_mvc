package app.controller;

import app.entity.Role;
import app.entity.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(ModelMap modelMap){
        List<User> list = service.getAllUsers();
        modelMap.addAttribute("list", list);
        return "index";
    }

    @RequestMapping("add")
    public String addUser(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return "adduser";
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

    @RequestMapping("updateuser")
    public ModelAndView editUser(@RequestParam Long id) {
        ModelAndView view = new ModelAndView("updateuser");
        User user = service.getUser(id);
        view.addObject("editUser", user);
        return view;
    }

    @RequestMapping("delete")
    public String deleteUser(@RequestParam Long id) {
        service.deleteUser(id);
        return "redirect:/admin/";
    }

}
