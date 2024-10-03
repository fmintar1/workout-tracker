package com.backend.workout_tracker_spring.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.workout_tracker_spring.model.WTModel;
import com.backend.workout_tracker_spring.repository.WTRepository;

@Service
@Transactional
public class WTService {

    @Autowired
    private WTRepository wtRepository;

    public List<WTModel> getAllWorkouts() {
        return wtRepository.findAll();
    }

    public List<WTModel> getWorkoutByCategory(String category) {
        return wtRepository.findByCategory(category);
    }

    public WTModel getWorkoutByName(String workoutName) {
        return wtRepository.findByWorkoutName(workoutName);
    }

    public List<WTModel> getAllWorkoutsByName(String workoutName) {
        List<WTModel> wtModelFilter = new ArrayList<>();
        for(WTModel l : getAllWorkouts()) {
            if (l.getWorkoutName().equalsIgnoreCase(workoutName)) {
                wtModelFilter.add(l);
            }
        }
        return wtModelFilter;
    }

    public WTModel updateWorkoutByName(String oldWorkoutName, WTModel wtModel) {
        WTModel oldWtModel = wtRepository.findByWorkoutName(oldWorkoutName);
        oldWtModel.setCategory(wtModel.getCategory());
        oldWtModel.setWorkoutName(wtModel.getWorkoutName());
        oldWtModel.setWeight(wtModel.getWeight());
        oldWtModel.setReps(wtModel.getReps());
        return wtRepository.save(oldWtModel);
    }

    public WTModel saveWorkout(WTModel wtModel) {
        return wtRepository.save(wtModel);
    }

    public void deleteWorkoutByName(String workoutName) {
        wtRepository.deleteByWorkoutName(workoutName);
    }

    public void deleteAllWorkoutsByName(String workoutName) {
        wtRepository.deleteAllWorkoutsByWorkoutName(workoutName);
    }
}
