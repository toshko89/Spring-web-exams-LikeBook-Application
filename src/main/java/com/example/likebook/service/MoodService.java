package com.example.likebook.service;

import com.example.likebook.model.Mood;
import com.example.likebook.model.enums.MoodEnum;
import com.example.likebook.repository.MoodRepo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MoodService {

    private final MoodRepo moodRepo;

    public MoodService(MoodRepo moodRepo) {
        this.moodRepo = moodRepo;
    }

    public Mood getMood(MoodEnum moodEnum) {
        return this.moodRepo.findByMoodName(moodEnum);
    }

    public void initMoodDB() {
        if (moodRepo.count() == 0) {
            List<Mood> moodList = Arrays.stream(MoodEnum.values())
                    .map(moodEnum -> new Mood().setMoodName(moodEnum))
                    .toList();

            moodRepo.saveAll(moodList);
        }
    }
}
