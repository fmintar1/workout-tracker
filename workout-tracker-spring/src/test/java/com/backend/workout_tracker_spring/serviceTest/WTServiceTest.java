package com.backend.workout_tracker_spring.serviceTest;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.backend.workout_tracker_spring.model.WTModel;
import com.backend.workout_tracker_spring.repository.WTRepository;
import com.backend.workout_tracker_spring.service.WTService;

@SpringBootTest
@ActiveProfiles("test")
public class WTServiceTest {
    
    @MockBean
    private WTRepository wtRepository;

    @Autowired
    private WTService wtService;

    private WTModel wtModel;
    private WTModel wtModel2;
    private WTModel wtModel3;
    private WTModel wtModel4;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        wtModel = new WTModel();
        wtModel.setCategory("TestCategory");
        wtModel.setWorkoutName("TestWorkoutName");
        wtModel.setWeight(100);
        wtModel.setReps(10);
        wtRepository.save(wtModel);
        wtModel2 = new WTModel();
        wtModel2.setCategory("TestCategory2");
        wtModel2.setWorkoutName("TestWorkoutName2");
        wtModel2.setWeight(50);
        wtModel2.setReps(20);
        wtRepository.save(wtModel2);
        wtModel3 = new WTModel();
        wtModel3.setCategory("TestCategory");
        wtModel3.setWorkoutName("TestWorkoutName3");
        wtModel3.setWeight(75);
        wtModel3.setReps(15);
        wtRepository.save(wtModel3);
        wtModel4 = new WTModel();
        wtModel4.setCategory("ChangedCategory");
        wtModel4.setWorkoutName("ChangedWorkoutName");
        wtModel4.setWeight(80);
        wtModel4.setReps(12);
        wtRepository.save(wtModel4);
    }

    @Test
    public void getWorkoutByCategory() {
        //Given
        when(wtService.getWorkoutByCategory("TestCategory")).thenReturn(List.of(wtModel, wtModel3));
        //When
        List<WTModel> actual = wtService.getWorkoutByCategory("TestCategory");
        //Then
        Assertions.assertEquals(List.of(wtModel, wtModel3), actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void getWorkoutByName() {
        //Given
        when(wtService.getWorkoutByName("TestWorkoutName")).thenReturn(wtModel);
        //When
        WTModel actual = wtService.getWorkoutByName("TestWorkoutName");
        //Then
        Assertions.assertEquals(wtModel, actual);
    }

    @Test
    public void getAllWorkoutsTest() {
        //Given
        when(wtService.getAllWorkouts()).thenReturn(List.of(wtModel, wtModel2, wtModel3));
        //When
        List<WTModel> actual = wtService.getAllWorkouts();
        //Then
        Assertions.assertEquals(3, actual.size());
    }

    @Test
    public void updateWorkoutByNameTest() {
        //Given
        when(wtService.getWorkoutByName("TestWorkoutName")).thenReturn(wtModel);
        when(wtService.saveWorkout(wtModel)).thenReturn(wtModel);
        when(wtService.updateWorkoutByName("TestWorkoutName", wtModel)).thenReturn(wtModel4);
        //When
        WTModel actual = wtService.updateWorkoutByName("TestWorkoutName", wtModel4);
        //Then
        Assertions.assertEquals(wtModel4.getWorkoutName(), actual.getWorkoutName());
        Assertions.assertEquals(wtModel4.getCategory(), actual.getCategory());
        Assertions.assertEquals(wtModel4.getReps(), actual.getReps());
        Assertions.assertEquals(wtModel4.getWeight(), actual.getWeight());
    }

    @Test
    public void saveWorkoutTest() {
        //Given
        when(wtService.saveWorkout(wtModel)).thenReturn(wtModel);
        //When
        WTModel actual = wtService.saveWorkout(wtModel);
        //Then
        Assertions.assertEquals(wtModel.getWorkoutName(), actual.getWorkoutName());
        Assertions.assertEquals(wtModel.getCategory(), actual.getCategory());
        Assertions.assertEquals(wtModel.getReps(), actual.getReps());
        Assertions.assertEquals(wtModel.getWeight(), actual.getWeight());
    }

    @Test
    public void deleteWorkoutTest() {
        //When
        wtService.deleteWorkoutByName("TestCategory");

        //Then
        Mockito.verify(wtRepository).deleteByWorkoutName("TestCategory");
    }
}
