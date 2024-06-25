package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.example.Entity.FoodType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

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


    public void createAnimal(Scanner scanner, EntityManager em) {
        System.out.println("Enter the name of the animal");
        String name = scanner.nextLine();

        int age = intConvert("Enter the age of the animal", scanner);

        FoodType food = foodtypeSelector(scanner);

        LocalDate arrivalDate = arrivalSelector(scanner);

        Animaux animal = Animaux.builder()
                .name(name)
                .age(age)
                .food(food)
                .arrivalDate(arrivalDate)
                .build();

        em.persist(animal);
        em.getTransaction().commit();

    }

    public void selectAnimalById(Scanner scanner, EntityManager em) {
        int id = intConvert("Enter the id of the animal", scanner);

        Animaux animal = em.find(Animaux.class, id);
        if (animal != null) {
            System.out.println(animal);
        } else {
            System.out.println("Animal not found");
        }
    }

    public void selectAnimalByName(Scanner scanner, EntityManager em) {
        System.out.println("Enter the name of the animal");
        String name = scanner.nextLine();
        Query query = em.createQuery("SELECT a FROM Animaux a WHERE a.name = :name", Animaux.class);
        query.setParameter("name", name);
        List<Animaux> animalList = query.getResultList();
        if (animalList.isEmpty()) {
            System.out.println("Animal not found");
        } else {
            animalList.forEach(System.out::println);
        }
    }

    public void selectAnimalByFood(Scanner scanner, EntityManager em) {
        FoodType food = foodtypeSelector(scanner);
        Query query = em.createQuery("SELECT a FROM Animaux a WHERE a.food = :food", Animaux.class);
        query.setParameter("food", food);
        List<Animaux> animalList = query.getResultList();
        if (animalList.isEmpty()) {
            System.out.println("Animal not found");
        } else {
            animalList.forEach(System.out::println);
        }
    }

    private int intConvert(String prompt, Scanner scanner) {
        int number = 0;
        do {
            try {
                System.out.println(prompt);
                number = Integer.parseInt(scanner.nextLine());
                if (number < 0 ) {
                    System.out.println("Please enter a valid number.");
                    number = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        } while (number == 0);
        return number;
    }

    private FoodType foodtypeSelector(Scanner scanner) {
        FoodType food = null;
        do {
            try {
                System.out.println("Enter the food type of the animal (CARNIVORE, HERBIVORE, OMNIVORE)");
                food = FoodType.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid food type. Please enter CARNIVORE, HERBIVORE, or OMNIVORE.");
            }
        } while (food == null);
        return food;
    }

    private LocalDate arrivalSelector(Scanner scanner) {
        System.out.println("Animal arrived today? (yes/no)");
        String arrival = scanner.nextLine();
        if (arrival.equalsIgnoreCase("yes")) {
            return LocalDate.now();
        } else {
            return dateChecker("Enter the arrival date of the animal", scanner);
        }
    }

    private LocalDate dateChecker(String prompt, Scanner scanner) {
        LocalDate date = null;
        do {
            try {
                System.out.println(prompt + " (format: yyyy-MM-dd)");
                date = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in 'yyyy-MM-dd' format.");
            }
        } while (date == null);
        return date;
    }

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
