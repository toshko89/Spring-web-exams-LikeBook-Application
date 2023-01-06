package com.example.likebook.service;

import com.example.likebook.model.Mood;
import com.example.likebook.model.Post;
import com.example.likebook.model.User;
import com.example.likebook.model.dto.PostDTO;
import com.example.likebook.model.enums.MoodEnum;
import com.example.likebook.repository.PostRepo;
import com.example.likebook.session.LoggedUserSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PostService {

    private final LoggedUserSession loggedUserSession;
    private final PostRepo postRepo;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final MoodService moodService;

    @Autowired
    public PostService(LoggedUserSession loggedUserSession, PostRepo postRepo, ModelMapper modelMapper, UserService userService, MoodService moodService) {
        this.loggedUserSession = loggedUserSession;
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.moodService = moodService;
    }

    public void addPost(PostDTO postDTO) {
        long userId = this.loggedUserSession.getId();

        User userById = userService.getUserById(userId);
        Mood mood = moodService.getMood(MoodEnum.valueOf(postDTO.getMood()));

        Post post = new Post();

        post.setMood(mood);
        post.setUser(userById);
        post.setContent(postDTO.getContent());

        this.postRepo.save(post);

    }

    public Set<Post> getAllUserPosts(long id) {
        return this.postRepo.findAllByCreatorId(id);
    }

    public Set<Post> getOtherPosts(long id) {
        return this.postRepo.findAllPostsByCreatorIdNot(id);
    }

    public void removePostById(long id) {
        this.postRepo.deleteById(id);
    }

    public void likePost(long postId) {
        Optional<Post> postById = this.postRepo.findById(postId);
        User userById = this.userService.getUserById(this.loggedUserSession.getId());
        if (postById.isPresent()) {
            Post post = postById.get();
            post.setLikes(post.getLikes() + 1);
            post.getUserLikes().add(userById);
            this.postRepo.save(post);
        }
    }

    public void initPostDB() {
        if (postRepo.count() == 0) {
            Post post = new Post()
                    .setLikes(1)
                    .setContent("Ne znam kakvo da napisha")
                    .setMood(this.moodService.getMood(MoodEnum.SAD))
                    .setUser(this.userService.getUserById(1));

            this.postRepo.save(post);

            Post post2 = new Post()
                    .setLikes(2)
                    .setContent("Ala-bala-nica")
                    .setMood(this.moodService.getMood(MoodEnum.INSPIRED))
                    .setUser(this.userService.getUserById(1));

            this.postRepo.save(post2);

            Post post3 = new Post()
                    .setLikes(2)
                    .setContent("Dobre che mina tozi izpit")
                    .setMood(this.moodService.getMood(MoodEnum.HAPPY))
                    .setUser(this.userService.getUserById(2));

            this.postRepo.save(post3);
        }
    }
}
