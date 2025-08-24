package com.example.todoapp.controller;

import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TaskRepository taskRepository;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("userCount", userRepository.count());
        model.addAttribute("taskCount", taskRepository.count());
        return "home";
    }
}
