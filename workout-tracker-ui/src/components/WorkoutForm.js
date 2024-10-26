import React, { useState, useEffect } from "react";
import ApiService from "../api/ApiService";

const WorkoutForm = ({ selectedWorkout, onFormSubmit }) => {
  const [category, setCategory] = useState("");
  const [workoutName, setWorkoutName] = useState("");
  const [weight, setWeight] = useState(0);
  const [reps, setReps] = useState(0);

  useEffect(() => {
    if (selectedWorkout) {
      setCategory(selectedWorkout.category);
      setWorkoutName(selectedWorkout.workoutName);
      setWeight(selectedWorkout.weight);
      setReps(selectedWorkout.reps);
    } else {
      clearForm();
    }
  }, [selectedWorkout]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const workoutModel = { category, workoutName, weight, reps };
    await onFormSubmit(workoutModel);
    clearForm();
  };

  const clearForm = () => {
    setCategory("");
    setWorkoutName("");
    setWeight(0);
    setReps(0);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="Workout-form-style">
        <input
          className="Workout-form-input Horizontal-center"
          type="text"
          placeholder="Category"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
          required
        />
        <input
          className="Workout-form-input Horizontal-center"
          type="text"
          placeholder="Workout Name"
          value={workoutName}
          onChange={(e) => setWorkoutName(e.target.value)}
          required
        />
        <input
          className="Workout-form-input Horizontal-center"
          type="number"
          placeholder="Weight"
          value={weight}
          onChange={(e) => setWeight(e.target.value)}
          required
        />
        <input
          className="Workout-form-input Horizontal-center"
          type="number"
          placeholder="Reps"
          value={reps}
          onChange={(e) => setReps(e.target.value)}
          required
        />
        <button type="submit" className="Workout-form-input">
          {selectedWorkout ? "Update" : "Create"} Workout
        </button>
      </div>
    </form>
  );
};

export default WorkoutForm;
