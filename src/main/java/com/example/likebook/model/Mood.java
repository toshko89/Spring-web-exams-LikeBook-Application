package com.example.likebook.model;

import com.example.likebook.model.enums.MoodEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "mood")
public class Mood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "mood_name",unique = true,nullable = false)
    @Enumerated(EnumType.STRING)
    private MoodEnum moodName;

    private String description;

    public Mood() {
    }

    public long getId() {
        return id;
    }

    public Mood setId(long id) {
        this.id = id;
        return this;
    }

    public MoodEnum getMoodName() {
        return moodName;
    }

    public Mood setMoodName(MoodEnum moodName) {
        this.moodName = moodName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Mood setDescription(String description) {
        this.description = description;
        return this;
    }
}
