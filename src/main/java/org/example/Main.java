package org.example;

import org.example.repository.AnimalRepository;
import org.example.service.AnimalService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

import static org.example.util.Menu.selectionMenu;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("animaux");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        AnimalRepository repository = new AnimalRepository(em);
        try {
            do {
                selectionMenu();
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1 -> AnimalService.createAnimal(scanner, repository);
                        case 2 -> AnimalService.selectAnimalById(scanner, repository);
                        case 3 -> AnimalService.selectAnimalByName(scanner, repository);
                        case 4 -> AnimalService.selectAnimalByFood(scanner, repository);
                        case 5 -> AnimalService.displayAllAnimals(repository);
                        case 6 -> System.out.println("Goodbye");
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number");
                }
            } while (choice != 6);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
            emf.close();
        }
    }
}
