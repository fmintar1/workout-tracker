import React from "react";
import "./App.css";
import Exercise from "./Exercise";

function App() {
  return (
    <div>
      <header>Chest Workout</header>
      <div>
        <Exercise name="Dumbbell Press" />
        <Exercise name="Incline Dumbbell Press" />
        <Exercise name="Cable Press" />
        <Exercise name="Skull Crusher" />
      </div>
    </div>
  );
}

export default App;
