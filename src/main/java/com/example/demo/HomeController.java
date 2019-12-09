package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    todoRepository TodoRepository;

    @RequestMapping("/")
    public String listtodo(Model model){
        model.addAttribute("todo", TodoRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String taskForm(Model model){
        model.addAttribute("Task", new todo());
        return "taskForm";
    }
    @PostMapping("/processTask")
    public String processtask(@Valid todo Task, BindingResult result){
        if (result.hasErrors()){
            return "taskForm";
        }
        TodoRepository.save(Task);
        return "redirect:/";
    }
}
