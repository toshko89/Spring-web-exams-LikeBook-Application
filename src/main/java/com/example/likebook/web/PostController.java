package com.example.likebook.web;

import com.example.likebook.model.dto.PostDTO;
import com.example.likebook.service.PostService;
import com.example.likebook.session.LoggedUserSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostController {

    private final LoggedUserSession loggedUserSession;
    private final PostService postService;

    @Autowired
    public PostController(LoggedUserSession loggedUserSession, PostService postService) {
        this.loggedUserSession = loggedUserSession;
        this.postService = postService;
    }


    @ModelAttribute
    public PostDTO initPostDTO() {
        return new PostDTO();
    }

    @GetMapping("/post-add")
    public String addPost() {
        if (!loggedUserSession.isLoggeinIn()) {
            return "redirect:/login";
        }

        return "post-add";
    }

    @PostMapping("/post-add")
    public String addPost(@Valid PostDTO postDTO, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (!loggedUserSession.isLoggeinIn()) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("PostDTO", postDTO);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.PostDTO", bindingResult);

            return "redirect:/post-add";
        }

        this.postService.addPost(postDTO);

        return "redirect:/home";
    }

    @GetMapping("/remove-post/{id}")
    public String removePost(@PathVariable long id) {
        if (!loggedUserSession.isLoggeinIn()) {
            return "redirect:/login";
        }

        this.postService.removePostById(id);

        return "redirect:/home";
    }

    @GetMapping("/like-post/{id}")
    public String likePost(@PathVariable long id) {
        if (!loggedUserSession.isLoggeinIn()) {
            return "redirect:/login";
        }

        this.postService.likePost(id);

        return "redirect:/home";
    }
}

