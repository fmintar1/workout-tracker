import axios from 'axios';

const API_URL = 'http://localhost:8080/workouts';

const ApiService = {
    getAllWorkouts: async () => {
        const response = await axios.get(`${API_URL}/all`);
        return response.data;
    },

    getWorkoutByName: async (workoutName) => {
        const response = await axios.get(`${API_URL}/name/${workoutName}`);
        return response.data;
    },

    getWorkoutsByCategory: async (categoryName) => {
        const response = await axios.get(`${API_URL}/category/${categoryName}`);
        return response.data;
    },

    createWorkout: async (workoutModel) => {
        const response = await axios.post(`${API_URL}/post`, workoutModel);
        return response.data;
    },

    updateWorkout: async (workoutName, newWorkoutModel) => {
        const response = await axios.put(`${API_URL}/update/${workoutName}`, newWorkoutModel);
        return response.data;
    },

    deleteWorkout: async (workoutName) => {
        const response = await axios.delete(`${API_URL}/delete/${workoutName}`);
        return response.data;
    },

    deleteAllWorkoutsByName: async (workoutName) => {
        const response = await axios.delete(`${API_URL}/deleteAllByName/${workoutName}`);
        return response.data;
    }
};

export default ApiService;