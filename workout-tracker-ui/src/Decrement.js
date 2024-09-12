import React, { useState } from 'react';

export const decrementCounterBy5 = (weight, setWeight) => {
    setWeight(weight > 0 ? weight - 5 : 0);
}

export const decrementCounter = (count, setCount) => {
    setCount(count > 0 ? count - 1 : 0);
}