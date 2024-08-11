package com.example.ems.controller;

import com.example.ems.model.UserDto;
import com.example.ems.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
       model.addAttribute("user", new UserDto());
        return "signup";
    }

    @PostMapping("/register/save")
    public String saveNewUser(UserDto userDto) {
        this.loginService.saveNewUser(userDto);
        return "redirect:/login";
    }

}
