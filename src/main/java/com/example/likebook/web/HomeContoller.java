package com.example.likebook.web;

import com.example.likebook.model.Post;
import com.example.likebook.service.PostService;
import com.example.likebook.session.LoggedUserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Controller
public class HomeContoller {

    private final LoggedUserSession loggedUserSession;
    private final PostService postService;

    @Autowired
    public HomeContoller(LoggedUserSession loggedUserSession, PostService postService) {
        this.loggedUserSession = loggedUserSession;
        this.postService = postService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){
        if(!loggedUserSession.isLoggeinIn()){
            return "redirect:/login";
        }

        Set<Post> allUserPosts = this.postService.getAllUserPosts(loggedUserSession.getId());
        Set<Post> otherUsersPosts = this.postService.getOtherPosts(loggedUserSession.getId());
        model.addAttribute("userPosts",allUserPosts);
        model.addAttribute("otherPosts",otherUsersPosts);
        return "home";
    }

}
