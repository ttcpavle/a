/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.javaapp0701.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.javaapp0701.domain.Predavac;
import rs.ac.bg.fon.javaapp0701.repository.PredavacRepository;

/**
 *
 * @author totic
 */

@Repository(value="jpa")
public class JPAPredavacRepository implements PredavacRepository {

    private final EntityManagerFactory emf;

    public JPAPredavacRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(Predavac predavac) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(predavac);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
 
    @Override
    public void update(Predavac predavac) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(predavac);   // merge = UPDATE if id exists
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
 
    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Predavac p = em.find(Predavac.class, id);  // find first, then remove
            if (p != null) {
                em.remove(p);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
 
    @Override
    public Predavac findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Predavac.class, id);
        } finally {
            em.close();
        }
    }
 
    @Override
    public List<Predavac> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Predavac.findAll", Predavac.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}
