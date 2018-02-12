package com.example.Forum.controllers;

import com.example.Forum.models.Comment;
import com.example.Forum.models.Topic;
import com.example.Forum.repositories.CategoryRepository;
import com.example.Forum.repositories.CommentRepository;
import com.example.Forum.repositories.TopicRepository;
import com.example.Forum.repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Calendar;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String category(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        model.addAttribute("category", categoryRepository.getOne(id));
        return "category";
    }

    @RequestMapping(value = "/{id}/topic/{topicid}", method = RequestMethod.GET)
    public String category(@PathVariable(value = "id") Long id, @PathVariable(value = "topicid") Long topicid, Model model) {
        model.addAttribute("topic", topicRepository.getOne(topicid));
        return "topic";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/{id}/topic/", method = RequestMethod.GET)
    public String getTopicForm(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("topic", new Topic());
        return "topicForm";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/{id}/topic/{topicid}/comment", method = RequestMethod.GET)
    public String getCommentForm(@PathVariable(value = "id") Long id, @PathVariable(value = "topicid") Long topicid, Model model) {
        model.addAttribute("comment", new Comment());
        return "commentForm";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/{id}/topic/", method = RequestMethod.POST)
    public String postTopicForm(@PathVariable(value = "id") Long id, Topic topic, Principal principal) {
        topic.setId(null);
        topic.setCategory(categoryRepository.getOne(id));
        topic.setUser(userCredentialsRepository.findByUsername(principal.getName()));
        topic.setCreationDate(Calendar.getInstance().getTime());
        topicRepository.save(topic);
        return "redirect:/category/" + id + "/topic/" + topic.getId();
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/{id}/topic/{topicid}/comment", method = RequestMethod.POST)
    public String postCommentForm(@PathVariable(value = "id") Long id, @PathVariable(value = "topicid") Long topicid, Comment comment, Principal principal) {
        comment.setId(null);
        comment.setTopic(topicRepository.getOne(topicid));
        comment.setUser(userCredentialsRepository.findByUsername(principal.getName()));
        comment.setCreationDate(Calendar.getInstance().getTime());
        commentRepository.save(comment);
        return "redirect:/category/" + id + "/topic/" + topicid;
    }


}
