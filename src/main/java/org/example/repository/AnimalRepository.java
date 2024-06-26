package org.example.repository;
import org.example.entity.Animaux;
import org.example.entity.FoodType;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AnimalRepository {
    private EntityManager em;

    public AnimalRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Animaux animal) {
        em.persist(animal);
        em.getTransaction().commit();
    }

    public Animaux findById(int id) {
        return em.find(Animaux.class, id);
    }

    public List<Animaux> findByName(String name) {
        Query query = em.createQuery("SELECT a FROM Animaux a WHERE a.name = :name", Animaux.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Animaux> findByFood(FoodType food) {
        Query query = em.createQuery("SELECT a FROM Animaux a WHERE a.food = :food", Animaux.class);
        query.setParameter("food", food);
        return query.getResultList();
    }
}
