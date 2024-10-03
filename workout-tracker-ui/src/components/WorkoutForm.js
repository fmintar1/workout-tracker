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
      <input
        type="text"
        placeholder="Category"
        value={category}
        onChange={(e) => setCategory(e.target.value)}
        required
      />
      <input
        type="text"
        placeholder="Workout Name"
        value={workoutName}
        onChange={(e) => setWorkoutName(e.target.value)}
        required
      />
      <input
        type="number"
        placeholder="Weight"
        value={weight}
        onChange={(e) => setWeight(e.target.value)}
        required
      />
      <input
        type="number"
        placeholder="Reps"
        value={reps}
        onChange={(e) => setReps(e.target.value)}
        required
      />
      <button type="submit">
        {selectedWorkout ? "Update" : "Create"} Workout
      </button>
    </form>
  );
};

export default WorkoutForm;
