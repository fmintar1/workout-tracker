import React, { useState, useEffect } from "react";
import "./App.css";
import WorkoutList from "./components/WorkoutList";
import WorkoutForm from "./components/WorkoutForm";
import ApiService from "./api/ApiService";

const App = () => {
  const [selectedWorkout, setSelectedWorkout] = useState(null);
  const [workoutsByCategory, setWorkoutsByCategory] = useState([]);

  const handleFormSubmit = async (workoutModel) => {
    if (selectedWorkout) {
      await ApiService.updateWorkout(selectedWorkout.workoutName, workoutModel);
    } else {
      await ApiService.createWorkout(workoutModel);
    }
    setSelectedWorkout(null);
    await fetchWorkouts();
  };

  const fetchWorkouts = async () => {
    const data = await ApiService.getAllWorkouts();
    const groupedWorkouts = data.reduce((acc, workout) => {
      if (!acc[workout.category]) {
        acc[workout.category] = [];
      }
      acc[workout.category].push(workout);
      return acc;
    }, {});
    setWorkoutsByCategory(groupedWorkouts);
  };

  useEffect(() => {
    fetchWorkouts();
  }, []);

  return (
    <div>
      <WorkoutForm
        selectedWorkout={selectedWorkout}
        onFormSubmit={handleFormSubmit}
      />
      <WorkoutList
        workoutsByCategory={workoutsByCategory}
        onWorkoutSelect={setSelectedWorkout} 
        fetchWorkouts={fetchWorkouts}/>
      <button onClick={async () => await fetchWorkouts()}> Render List </button>
    </div>
  );
};

export default App;
