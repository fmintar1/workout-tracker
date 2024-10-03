package com.backend.workout_tracker_spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="WTMODEL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WTModel {
    
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="CATEGORY")
    @JsonProperty("category")
    String category;

    @Column(name="WORKOUTNAME")
    @JsonProperty("workoutName")
    String workoutName;

    @Column(name="WEIGHT")
    @JsonProperty("weight")
    int weight;

    @Column(name="REPS")
    @JsonProperty("reps")
    int reps;
}
