package app.controller;

import app.entity.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserService service;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(ModelMap modelMap){
        List<User> list = service.getAllUsers();
        modelMap.addAttribute("list", list);
        return "index";
    }

    @RequestMapping("/add")
    public String addUser(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return "adduser";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("users") User user) {
        service.addUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("users") User user) {
        service.update(user);
        return "redirect:/";
    }

    @RequestMapping("/updateuser")
    public ModelAndView editUser(@RequestParam Long id) {
        ModelAndView view = new ModelAndView("updateuser");
        User user = service.getUser(id);
        view.addObject("editUser", user);
        return view;
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        service.deleteUser(id);
        return "redirect:/";
    }

}
