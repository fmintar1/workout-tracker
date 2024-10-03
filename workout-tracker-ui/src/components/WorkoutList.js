import React, { useState, useEffect } from "react";
import ApiService from "../api/ApiService";
import { increaseWeightByFive, increaseReps } from "../Increment";
import { decreaseWeightByFive, decreaseReps } from "../Decrement";

const WorkoutList = ({ workoutsByCategory, onWorkoutSelect, fetchWorkouts }) => {
  const handleDelete = async (workoutName) => {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete this workout?"
    );

    if (confirmDelete) {
      await ApiService.deleteWorkout(workoutName);
      await fetchWorkouts();
    }
  };

  const handleDeleteAllByName = async (workoutName) => {
    const confirmDelete = window.confirm (`Are you sure you want to delete all workouts with the name ${workoutName}?`);

    if (confirmDelete) {
      await ApiService.deleteAllWorkoutsByName(workoutName);
      await fetchWorkouts();
    }
  }

  return (
    <div>
      <h1>Workout List</h1>
      {Object.keys(workoutsByCategory).map((category) => (
        <div key={category}>
          <h2>{category}</h2>
          <ul>
            {workoutsByCategory[category].map((workout) => (
              <li key={workout.id}>
                {workout.workoutName} - {workout.weight} lbs
                <button
                  onClick={async () => {
                    await increaseWeightByFive(workout.workoutName);
                    fetchWorkouts();
                  }}
                >
                  +
                </button>
                <button
                  onClick={async () => {
                    await decreaseWeightByFive(workout.workoutName);
                    fetchWorkouts();
                  }}
                >
                  -
                </button>
                - {workout.reps} reps
                <button
                  onClick={async () => {
                    await increaseReps(workout.workoutName);
                    fetchWorkouts();
                  }}
                >
                  +
                </button>
                <button
                  onClick={async () => {
                    await decreaseReps(workout.workoutName);
                    fetchWorkouts();
                  }}
                >
                  -
                </button>
                <button
                  onClick={async () => {
                    await onWorkoutSelect(workout);
                  }}
                >
                  Edit
                </button>
                <button
                  onClick={async () => {
                    await handleDelete(workout.workoutName);
                  }}
                >
                  Delete
                </button>
                <button onClick={async () => {
                  await handleDeleteAllByName(workout.workoutName);
                }}>
                  Delete all {workout.workoutName}
                </button>
              </li>
            ))}
          </ul>
        </div>
      ))}
    </div>
  );
};

export default WorkoutList;
