package com.backend.workout_tracker_spring.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.backend.workout_tracker_spring.controller.WTController;
import com.backend.workout_tracker_spring.model.WTModel;
import com.backend.workout_tracker_spring.service.WTService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(WTController.class)
@AutoConfigureMockMvc
class WTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WTService wtService;

    @Autowired
    private ObjectMapper objectMapper;

    WTModel wtModel;
    WTModel wtModel2;

    @BeforeEach
    public void setUp() {
        wtModel = new WTModel(1L, "TestCategory", "TestWorkout", 100, 10);
        wtModel2 = new WTModel(2L, "TestCategory2", "TestWorkout2", 110, 9);
    }

    @Test
    void getAllWorkoutsTest() throws Exception {
        // When
        when(wtService.getAllWorkouts()).thenReturn(List.of(wtModel, wtModel2));
        // Then
        mockMvc.perform(get("/workouts/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getWorkoutByNameTest() throws Exception {
        // When
        when(wtService.getWorkoutByName("TestWorkout")).thenReturn(wtModel);
        // Then
        mockMvc.perform(get("/workouts/name/TestWorkout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.workoutName").value("TestWorkout"))
                .andExpect(jsonPath("$.category").value("TestCategory"))
                .andExpect(jsonPath("$.weight").value(100))
                .andExpect(jsonPath("$.reps").value(10));
    }

    @Test
    void getWorkoutByCategoryTest() throws Exception {
        // When
        when(wtService.getWorkoutByCategory("TestCategory")).thenReturn(List.of(wtModel, wtModel2));
        // Then
        mockMvc.perform(get("/workouts/category/TestCategory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void updateWorkoutByNameTest() throws Exception {
        // When
        when(wtService.getWorkoutByName("TestWorkoutName")).thenReturn(wtModel2);
        when(wtService.updateWorkoutByName("TestWorkoutName", wtModel2)).thenReturn(wtModel2);
    
        // Then
        mockMvc.perform(put("/workouts/update/TestWorkoutName")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wtModel2)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateNullWorkoutNameTest() throws Exception {
        // When
        when(wtService.updateWorkoutByName("NonExistentWorkoutName", wtModel)).thenReturn(null);

        // Then
        mockMvc.perform(put("/workouts/update/NonExistentWorkoutName")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wtModel)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    

    @Test
    void saveWorkoutTest() throws Exception {
        // When
        when(wtService.saveWorkout(any(WTModel.class))).thenReturn(any(WTModel.class));
        // Then
        mockMvc.perform(post("/workouts/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wtModel)))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteWorkoutByNameTest() throws Exception {
        // When
        when(wtService.getWorkoutByName("TestWorkoutName")).thenReturn(wtModel);
        doNothing().when(wtService).deleteWorkoutByName("TestWorkoutName");

        // Then
        mockMvc.perform(delete("/workouts/delete/TestWorkoutName"))
            .andExpect(status().isNoContent());
    }

    @Test
    void deleteNonExistentWorkoutNameTest() throws Exception {
        // When
        when(wtService.getWorkoutByName("TestWorkoutName")).thenReturn(wtModel);
        doThrow(new RuntimeException("Workout not found")).when(wtService).deleteWorkoutByName("NonExistentWorkoutName");

        // Then
        mockMvc.perform(delete("/workouts/delete/NonExistentWorkoutName"))
                .andExpect(status().isNotFound());
    }
}
