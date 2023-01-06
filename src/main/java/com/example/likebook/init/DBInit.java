package com.example.likebook.init;

import com.example.likebook.service.MoodService;
import com.example.likebook.service.PostService;
import com.example.likebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final MoodService moodService;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public DBInit(MoodService moodService, UserService userService, PostService postService) {
        this.moodService = moodService;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.userService.initUsersDB();
        this.moodService.initMoodDB();
        this.postService.initPostDB();

    }
}
