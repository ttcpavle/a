/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.javaapp0701.repository;

import java.util.List;
import rs.ac.bg.fon.javaapp0701.domain.Predavac;

/**
 *
 * @author totic
 */


public interface PredavacRepository {
    void save(Predavac predavac);
    void update(Predavac predavac);
    void delete(Long id);
    Predavac findById(Long id);
    List<Predavac> findAll();
}
