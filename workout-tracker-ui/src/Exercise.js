// import React, { useState } from "react";
// import { incrementCounterBy5, incrementCounter } from "./Increment";
// import { decrementCounterBy5, decrementCounter } from "./Decrement";

// const Exercise = ({ name }) => {
//   const [weight, setWeight] = useState(0);
//   const [count, setCount] = useState(0);

//   return (
//     <div>
//       <div className="Workout-measurement-grid">
//         <div>{name}</div>
//         <div className="Buttons-gap">
//           <button className="btn btn-success" onClick={() => incrementCounterBy5(weight, setWeight)}>+5</button>
//           <button className="btn btn-danger" onClick={() => decrementCounterBy5(weight, setWeight)}>-5</button>
//         </div>
//         <div className="Decrease-margin-by-100">Current record: {weight} lbs</div>
//         <div className="Buttons-gap">
//           <button className="btn btn-success" onClick={() => incrementCounter(count, setCount)}>+1</button>
//           <button className="btn btn-danger" onClick={() => decrementCounter(count, setCount)}>-1</button>
//         </div>
//         <div className="Decrease-margin-by-100">Current reps: {count}</div>
//       </div>
//     </div>
//   );
// }

// export default Exercise;
