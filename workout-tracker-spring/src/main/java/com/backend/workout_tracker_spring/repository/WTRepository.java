package com.backend.workout_tracker_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.workout_tracker_spring.model.WTModel;

import jakarta.transaction.Transactional;

import java.util.List;


@Repository
public interface WTRepository extends JpaRepository <WTModel, Long>{
    
    List<WTModel> findByCategory(String category);
    WTModel findByWorkoutName(String workoutName);
    void deleteByWorkoutName(String workoutName);

    @Modifying
    @Transactional
    @Query("DELETE FROM WTModel WHERE workoutName = :workoutName")
    void deleteAllWorkoutsByWorkoutName(String workoutName);
}
