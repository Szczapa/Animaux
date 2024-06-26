package org.example.service;

import org.example.entity.Animaux;
import org.example.entity.FoodType;
import org.example.repository.AnimalRepository;
import org.example.util.InputUtility;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AnimalService {

    public static void createAnimal(Scanner scanner, AnimalRepository repository) {
        System.out.println("Enter the name of the animal");
        String name = scanner.nextLine();

        int age = InputUtility.intConvert("Enter the age of the animal", scanner);

        FoodType food = InputUtility.foodTypeSelector(scanner);

        LocalDate arrivalDate = InputUtility.arrivalSelector(scanner);

        Animaux animal = Animaux.builder()
                .name(name)
                .age(age)
                .food(food)
                .arrivalDate(arrivalDate)
                .build();

        repository.save(animal);
    }

    public static void selectAnimalById(Scanner scanner, AnimalRepository repository) {
        int id = InputUtility.intConvert("Enter the id of the animal", scanner);

        Animaux animal = repository.findById(id);
        if (animal != null) {
            System.out.println(animal);
        } else {
            System.out.println("Animal not found");
        }
    }

    public static void selectAnimalByName(Scanner scanner, AnimalRepository repository) {
        System.out.println("Enter the name of the animal");
        String name = scanner.nextLine();

        List<Animaux> animalList = repository.findByName(name);
        if (animalList.isEmpty()) {
            System.out.println("Animal not found");
        } else {
            animalList.forEach(System.out::println);
        }
    }

    public static void selectAnimalByFood(Scanner scanner, AnimalRepository repository) {
        FoodType food = InputUtility.foodTypeSelector(scanner);

        List<Animaux> animalList = repository.findByFood(food);
        if (animalList.isEmpty()) {
            System.out.println("Animal not found");
        } else {
            animalList.forEach(System.out::println);
        }
    }
    public static void displayAllAnimals(AnimalRepository repository) {
        List<Animaux> animalList = repository.findAll();
        if (animalList.isEmpty()) {
            System.out.println("No animals found");
        } else {
            animalList.forEach(System.out::println);
        }
    }
}
