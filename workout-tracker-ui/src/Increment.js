import React, { useState } from 'react';

export const incrementCounterBy5 = (weight, setWeight) => {
    setWeight(weight + 5);
}

export const incrementCounter = (count, setCount) => {
    setCount(count + 1);
}