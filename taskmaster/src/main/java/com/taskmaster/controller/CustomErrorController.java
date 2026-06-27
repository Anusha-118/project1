package com.taskmaster.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage = "Something went wrong.";
        String errorCode = "Error";

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                errorCode = "404";
                errorMessage = "The page you are looking for does not exist.";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorCode = "500";
                errorMessage = "An internal server error occurred. Please try again later.";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                errorCode = "403";
                errorMessage = "You do not have permission to access this page.";
            } else {
                errorCode = String.valueOf(statusCode);
                errorMessage = "An unexpected error occurred.";
            }
        }

        model.addAttribute("errorCode", errorCode);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("activePage", "error");
        return "error";
    }
}
