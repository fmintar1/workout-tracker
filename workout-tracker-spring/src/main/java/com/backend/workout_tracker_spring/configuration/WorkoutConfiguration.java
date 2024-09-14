// package com.backend.workout_tracker_spring.configuration;

// import javax.sql.DataSource;

// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

// @Configuration
// public class WorkoutConfiguration {
    
//     @Bean
//     public DataSource dataSource() {
//         return DataSourceBuilder.create()
//         .url("jdbc:h2:file:C:/Users/fmint/projects/data/workout-tracker")
//         .driverClassName("org.h2.Driver")
//         .username("sa")
//         .password("")
//         .build();
//     }

//     @Bean
//     public LocalContainerEntityManagerFactoryBean entityManagerFActory() {
//         LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//         em.setDataSource(dataSource());
//         em.setPackagesToScan(new String[] { "your.package.name" });

//     }
// }
