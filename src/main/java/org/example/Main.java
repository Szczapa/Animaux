package org.example;

import org.example.Entity.Animaux;
import org.example.Util.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Scanner;

import static org.example.Util.Menu.selectionMenu;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("animaux");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Animaux animal = new Animaux();


    do {
        selectionMenu();
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1 -> animal.createAnimal(scanner, em);
                case 2 -> animal.selectAnimalById(scanner, em);
                case 3 -> animal.selectAnimalByName(scanner, em);
                case 4 -> animal.selectAnimalByFood(scanner, em);
                case 5 -> System.out.println("Goodbye");
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a number");
        }
    } while (choice != 5);


    }


}