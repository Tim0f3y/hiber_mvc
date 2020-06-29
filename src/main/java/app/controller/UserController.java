package app.controller;

import app.entity.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String UserPage(ModelMap modelMap, Authentication auth){
        User user =(User) auth.getPrincipal();
        List<User> list = new ArrayList<>();
        list.add(user);
        modelMap.addAttribute("list", list);
        return "user";
    }

}
