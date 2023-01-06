package com.example.likebook.repository;

import com.example.likebook.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PostRepo extends JpaRepository<Post,Long> {
    Set<Post> findAllByCreatorId(long id);

    Set<Post> findAllPostsByCreatorIdNot(long id);
}
