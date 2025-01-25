package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Level;

@Controller
@Log
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        log.log(Level.INFO, "New user " + user + " was created");
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user){
        userService.updateUser(user);
        log.log(Level.INFO, "User " + user.getId() + " was updated");
        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        log.log(Level.INFO, "User " + id + " was deleted");
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String showAll(Model model){
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }
}
