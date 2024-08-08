package com.bhoper.controller;

import com.bhoper.dto.UserCreateRequest;
import com.bhoper.model.User;
import com.bhoper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("payload", new User());
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute("payload")
                                               UserCreateRequest userCreateRequest) {
        this.userService.createUser(userCreateRequest);
        return "redirect:http://nginx/home";
    }

}