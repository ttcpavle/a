/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.javaapp0701.repository.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.javaapp0701.domain.Predavac;
import rs.ac.bg.fon.javaapp0701.repository.PredavacRepository;

/**
 *
 * @author totic
 */
@Repository(value="hibernate")
public class HibernatePredavacRepository implements PredavacRepository {

    private final SessionFactory sessionFactory;

    public HibernatePredavacRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Ideja:
    // napraviti novi session
    // otvoriti transakciju
    // pozvati neku od funkijca: persist(), merge(), remove(), get(), createNamedQuery().getResultList()
    // transakcija commmit ili rollback
    @Override
    public void save(Predavac predavac) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                session.persist(predavac);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }
 
    @Override
    public void update(Predavac predavac) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                session.merge(predavac);  // merge = UPDATE if id exists
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }
 
    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                Predavac p = session.get(Predavac.class, id);  // get first, then remove
                if (p != null) {
                    session.remove(p);
                }
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }
 
    @Override
    public Predavac findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Predavac.class, id);
        }
    }
 
    @Override
    public List<Predavac> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createNamedQuery("Predavac.findAll", Predavac.class)
                          .getResultList();
        }
    }
}
