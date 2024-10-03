import React from 'react';
import ApiService from './api/ApiService';

export const increaseWeightByFive = async (workoutName) => {
    const data = await ApiService.getWorkoutByName(workoutName);
    data.weight += 5;
    await ApiService.updateWorkout(workoutName, data);
};

export const increaseReps = async (workoutName) => {
    const data = await ApiService.getWorkoutByName(workoutName);
    data.reps += 1;
    await ApiService.updateWorkout(workoutName, data);
};