package com.example.likebook.repository;

import com.example.likebook.model.Mood;
import com.example.likebook.model.enums.MoodEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoodRepo extends JpaRepository<Mood,Long> {
    Mood findByMoodName(MoodEnum name);
}
