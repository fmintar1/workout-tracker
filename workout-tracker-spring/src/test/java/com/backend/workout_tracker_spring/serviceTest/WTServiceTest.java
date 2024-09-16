package com.backend.workout_tracker_spring.serviceTest;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    }

    @Test
    public void getWorkoutByCategory() {
        //Given
        when(wtRepository.findByCategory("TestCategory")).thenReturn(List.of(wtModel, wtModel3));
        //When
        List<WTModel> actual = wtService.getWorkoutByCategory("TestCategory");
        //Then
        Assertions.assertEquals(List.of(wtModel, wtModel3), actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void getWorkoutByName() {
        //Given
        when(wtRepository.findByWorkoutName("TestWorkoutName")).thenReturn(wtModel);
        //When
        WTModel actual = wtService.getWorkoutByName("TestWorkoutName");
        //Then
        Assertions.assertEquals(wtModel, actual);
    }

    @Test
    public void getAllWorkouts() {
        //Given
        when(wtRepository.findAll()).thenReturn(List.of(wtModel, wtModel2, wtModel3));
        //When
        List<WTModel> actual = wtService.getAllWTModels();
        //Then
        Assertions.assertEquals(3, actual.size());
    }
}
