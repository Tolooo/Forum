package com.example.Forum.controllers;

import com.example.Forum.models.Category;
import com.example.Forum.models.UserCredentials;
import com.example.Forum.repositories.CategoryRepository;
import com.example.Forum.repositories.TopicRepository;
import com.example.Forum.repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TopicRepository topicRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("category", categoryRepository.findCategoriesNotHavingParents());
        model.addAttribute("latestTopics", topicRepository.findFirst5ByOrderByCreationDateDesc());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserCredentials());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("user") UserCredentials user) {
        userCredentialsRepository.save(user);
        return "redirect:/";
    }
}
