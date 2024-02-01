package com.amigoscode.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class TemplateController {
    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/courses")
    String getCourses() {
        return "courses";
    }
}
