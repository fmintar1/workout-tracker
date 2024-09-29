package com.backend.workout_tracker_spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.workout_tracker_spring.model.WTModel;
import com.backend.workout_tracker_spring.service.WTService;

@RestController
@RequestMapping("/workouts")
public class WTController {

    @Autowired
    private WTService wtService;

    @GetMapping("/all")
    public ResponseEntity<List<WTModel>> getAllWorkouts() {
        List<WTModel> wtModelList = wtService.getAllWorkouts();
        return ResponseEntity.ok(wtModelList);
    }

    @GetMapping("/name/{workoutName}")
    public ResponseEntity<WTModel> getWorkoutByName(@PathVariable String workoutName) {
        WTModel wtModel = wtService.getWorkoutByName(workoutName);
        return ResponseEntity.ok(wtModel);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<WTModel>> getWorkoutByCategory(@PathVariable String category) {
        List<WTModel> wtModelList = wtService.getWorkoutByCategory(category);
        return ResponseEntity.ok(wtModelList);
    }

    @PutMapping("/update/{workoutName}")
    public ResponseEntity<WTModel> updateWorkoutByName(@PathVariable String workoutName, @RequestBody WTModel wtModel) {
        WTModel oldWtModel = wtService.getWorkoutByName(workoutName);
        if (oldWtModel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wtService.updateWorkoutByName(workoutName, wtModel), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<WTModel> saveWorkout(@RequestBody WTModel wtModel) {
        try {
            WTModel savedWorkout = wtService.saveWorkout(wtModel);
            return new ResponseEntity<>(savedWorkout, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Handle validation errors or bad requests
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{workoutName}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable String workoutName) {
        WTModel wtModel = wtService.getWorkoutByName(workoutName);
        if (wtModel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        wtService.deleteWorkoutByName(workoutName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
