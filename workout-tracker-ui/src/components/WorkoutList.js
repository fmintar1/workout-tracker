import React, { useState, useEffect } from "react";
import ApiService from "../api/ApiService";
import { increaseWeightByFive, increaseReps } from "../Increment";
import { decreaseWeightByFive, decreaseReps } from "../Decrement";

const WorkoutList = ({
  workoutsByCategory,
  onWorkoutSelect,
  fetchWorkouts,
}) => {
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
    const confirmDelete = window.confirm(
      `Are you sure you want to delete all workouts with the name ${workoutName}?`
    );

    if (confirmDelete) {
      await ApiService.deleteAllWorkoutsByName(workoutName);
      await fetchWorkouts();
    }
  };

  const handleDeleteAllByCategory = async (category) => {
    const confirmDelete = window.confirm(
      `Are you sure you want to delete all workouts under category ${category}?`
    );

    if (confirmDelete) {
      await ApiService.deleteAllWorkoutsWithSameCategory(category);
      await fetchWorkouts();
    }
  };

  return (
    <div>
      <h1 className="Horizontal-center">WORKOUT LIST</h1>
      <div className="Workouts-grid">
        {Object.keys(workoutsByCategory).map((category) => (
          <div key={category}>
            <div className="Category-style">
              <h2>{category}</h2>
              <button
                className="Workout-form-input Workout-form-style Delete-all-workouts-style special"
                onClick={async () => {
                  await handleDeleteAllByCategory(category);
                }}
              >
                delete all {category} workouts
              </button>
            </div>
            <ul>
              {workoutsByCategory[category].map((workout) => (
                <li className="List-style" key={workout.id}>
                  {workout.workoutName} - {workout.weight} lbs
                  <button
                    className="Workout-form-input Non-delete-all-buttons special"
                    style={{border: '5px solid lime', background: 'lime', color: 'black', fontSize: '25px', padding: '0px 0px', height: 'auto', width: 'auto', lineHeight: '1', alignItems: 'center'}} onClick={async () => {
                      await increaseWeightByFive(workout.workoutName);
                      fetchWorkouts();
                    }}
                  >
                    +
                  </button>
                  <button
                    className="Workout-form-input Non-delete-all-buttons special"
                    onClick={async () => {
                      await decreaseWeightByFive(workout.workoutName);
                      fetchWorkouts();
                    }}
                  >
                    -
                  </button>
                  - {workout.reps} reps
                  <button
                    className="Workout-form-input Non-delete-all-buttons special"
                    onClick={async () => {
                      await increaseReps(workout.workoutName);
                      fetchWorkouts();
                    }}
                  >
                    +
                  </button>
                  <button
                    className="Workout-form-input Non-delete-all-buttons special"
                    onClick={async () => {
                      await decreaseReps(workout.workoutName);
                      fetchWorkouts();
                    }}
                  >
                    -
                  </button>
                  <button
                    className="Workout-form-input Non-delete-all-buttons special"
                    onClick={async () => {
                      await onWorkoutSelect(workout);
                    }}
                  >
                    Edit
                  </button>
                  <button
                    className="Workout-form-input Non-delete-all-buttons special"
                    onClick={async () => {
                      await handleDelete(workout.workoutName);
                    }}
                  >
                    Delete
                  </button>
                  <button
                    className="Delete-all-workouts-same-name Workout-form-input Delete-all-workouts-style special"
                    onClick={async () => {
                      await handleDeleteAllByName(workout.workoutName);
                    }}
                  >
                    Delete all {workout.workoutName} workouts
                  </button>
                </li>
              ))}
            </ul>
          </div>
        ))}
      </div>
    </div>
  );
};

export default WorkoutList;
