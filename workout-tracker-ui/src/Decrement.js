import React, { useState } from 'react';
import ApiService from './api/ApiService';

export const decreaseWeightByFive = async (workoutName) => {
    const data = await ApiService.getWorkoutByName(workoutName);
    data.weight > 0 ? data.weight -= 5 : data.weight = 0;
    await ApiService.updateWorkout(workoutName, data);
};

export const decreaseReps = async (workoutName) => {
    const data = await ApiService.getWorkoutByName(workoutName);
    data.reps > 0 ? data.reps -= 1 : data.reps = 0;
    await ApiService.updateWorkout(workoutName, data);
};