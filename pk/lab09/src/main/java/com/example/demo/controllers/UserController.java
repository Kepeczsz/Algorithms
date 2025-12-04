package com.example.demo.controllers;


import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/adduser")
    public String userForm(Model model)
    {
        model.addAttribute("user", new User());
        return "add-user";
    }
    @PostMapping("/adduser")public String userSubmit(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "add-user";
        }
        userService.AddUser(user);
        return "add-user";
    }
}
