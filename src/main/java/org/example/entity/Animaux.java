package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Animaux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private LocalDate arrivalDate;
    @Enumerated(EnumType.STRING)
    private FoodType food;

    // Getters and setters can be added if needed

    @Override
    public String toString() {
        return "Animaux{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", arrivalDate=" + arrivalDate +
                ", food=" + food +
                '}';
    }
}
