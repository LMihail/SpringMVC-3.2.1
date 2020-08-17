package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import web.Model.User;
import web.service.UserServiceImpl;

import java.util.List;

@Controller
public class UserController {
    private UserServiceImpl userService;

    @Autowired(required = true)
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    //список юзеров
    @GetMapping(value = "users")
    public String listUsers(Model model) {
        List<User> userList = userService.listUser();
        model.addAttribute("listusers", userList);
        return "users";
    }

    //удаление
    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/users";
    }

    //изменение
    @GetMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users_edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    //добавление
    @GetMapping("add/{id}")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "users_add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
}
