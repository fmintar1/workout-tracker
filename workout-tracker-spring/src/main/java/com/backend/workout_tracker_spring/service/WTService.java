package com.backend.workout_tracker_spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.workout_tracker_spring.model.WTModel;
import com.backend.workout_tracker_spring.repository.WTRepository;

@Service
public class WTService {
    
    @Autowired
    private WTRepository wtRepository;

    public List<WTModel> getAllWTModels () {
        return wtRepository.findAll();
    }

    public List<WTModel> getWorkoutByCategory (String category) {
        return wtRepository.findByCategory(category);
    }

    public WTModel getWorkoutByName (String workoutName) {
        return wtRepository.findByWorkoutName(workoutName);
    }
}
