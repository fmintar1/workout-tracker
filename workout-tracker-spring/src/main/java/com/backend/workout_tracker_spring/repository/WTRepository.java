package com.backend.workout_tracker_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.workout_tracker_spring.model.WTModel;
import java.util.List;


@Repository
public interface WTRepository extends JpaRepository <WTModel, Integer>{
    
    List<WTModel> findByCategory(String category);
    WTModel findByWorkoutName(String workoutName);
}
