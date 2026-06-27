package com.taskmaster.controller;

import com.taskmaster.entity.User;
import com.taskmaster.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm(HttpSession session, @RequestParam(value = "registered", required = false) String registered, Model model) {
        if (session.getAttribute("user") != null) {
            return "redirect:/dashboard";
        }
        if (registered != null) {
            model.addAttribute("successMessage", "Registration successful! Please sign in.");
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        // Validate using UserService and MySQL database lookup
        if (userService.authenticate(username, password)) {
            session.setAttribute("user", username);
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid username or password.");
        model.addAttribute("username", username);
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/dashboard";
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "All fields are required.");
            model.addAttribute("username", username);
            return "register";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            model.addAttribute("username", username);
            return "register";
        }

        try {
            userService.registerUser(new User(username, password));
            return "redirect:/login?registered=true";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("username", username);
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
