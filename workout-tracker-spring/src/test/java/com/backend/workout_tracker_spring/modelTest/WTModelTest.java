package com.backend.workout_tracker_spring.modelTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.workout_tracker_spring.model.WTModel;

public class WTModelTest {

    private WTModel wtModel;

    @BeforeEach
    public void setup() {
        wtModel = new WTModel();
    }

    @AfterEach
    public void tearDown() {
        wtModel = null;
    }

    @Test
    void setCategoryTest() {
        //Given
        wtModel.setCategory("Test");
        String expected = "Test";
        //When
        String actual = wtModel.getCategory();
        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void setWorkoutNameTest() {
        //Given
        wtModel.setWorkoutName("Test");
        String expected = "Test";
        //When
        String actual = wtModel.getWorkoutName();
        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void setWeightTest() {
        //Given
        wtModel.setWeight(100);
        int expected = 100;
        //When
        int actual = wtModel.getWeight();
        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void setRepsTest() {
        //Given
        wtModel.setReps(10);
        int expected = 10;
        //When
        int actual = wtModel.getReps();
        //Then
        Assertions.assertEquals(expected, actual);
    }
}
