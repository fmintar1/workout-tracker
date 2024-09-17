package com.backend.workout_tracker_spring.controllerTest;

import static org.mockito.Mockito.when;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.backend.workout_tracker_spring.controller.WTController;
import com.backend.workout_tracker_spring.model.WTModel;
import com.backend.workout_tracker_spring.service.WTService;

@WebMvcTest(WTController.class)
public class WTControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WTService wtService;

    @Test
    public void getAllWorkoutsTest() throws Exception {
        //Given
        WTModel wtModel = new WTModel(1L, "TestCategory", "TestWorkout", 100, 10);
        WTModel wtModel2 = new WTModel(2L, "TestCategory2", "TestWorkout2", 110, 9);
        //When
        when(wtService.getAllWorkouts()).thenReturn(List.of(wtModel, wtModel2));
        //Then
        mockMvc.perform(get("/workouts/all"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void getWorkoutByNameTest() throws Exception{
        //Given
        WTModel wtModel = new WTModel(1L, "TestCategory", "TestWorkout", 100, 10);
        //When
        when(wtService.getWorkoutByName("TestWorkout")).thenReturn(wtModel);
        //Then
        mockMvc.perform(get("/workouts/name/TestWorkout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.workoutName").value("TestWorkout"))
                .andExpect(jsonPath("$.category").value("TestCategory"))
                .andExpect(jsonPath("$.weight").value(100))
                .andExpect(jsonPath("$.reps").value(10));
    }

    @Test
    public void getWorkoutByCategoryTest() throws Exception {
        //Given
        WTModel wtModel = new WTModel(1L, "TestCategory", "TestWorkout", 100, 10);
        WTModel wtModel2 = new WTModel(2L, "TestCategory", "TestWorkout2", 110, 9);
        //When
        when(wtService.getWorkoutByCategory("TestCategory")).thenReturn(List.of(wtModel, wtModel2));
        //Then
        mockMvc.perform(get("/workouts/category/TestCategory"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2));
    }

    // @Test
    // public void updateWorkoutByNameTest() throws Exception {
    //         // Given
    // WTModel wtModel = new WTModel(1L, "TestCategory", "TestWorkoutName", 100, 10);
    // WTModel wtModel2 = new WTModel(1L, "ChangedCategory", "ChangedWorkoutName", 80, 12);

    // // When
    // when(wtService.saveWorkout(wtModel)).thenReturn(wtModel);
    // when(wtService.updateWorkoutByName("TestWorkoutName", wtModel2)).thenReturn(wtModel2);

    // // Then
    // mockMvc.perform(put("/workouts/update/TestWorkoutName")
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .content("{\"id\":1,\"category\":\"ChangedCategory\",\"workoutName\":\"ChangedWorkoutName\",\"weight\":80,\"reps\":12}"))
    //         .andExpect(status().isOk())
    //         .andExpect(jsonPath("$.id").value(1L))
    //         .andExpect(jsonPath("$.category").value("ChangedCategory"))
    //         .andExpect(jsonPath("$.workoutName").value("ChangedWorkoutName"))
    //         .andExpect(jsonPath("$.weight").value(80))
    //         .andExpect(jsonPath("$.reps").value(12));
    // } 
}
