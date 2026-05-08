/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.javaapp0701.service;

import java.util.List;
import rs.ac.bg.fon.javaapp0701.dto.PredavacDto;

/**
 *
 * @author totic
 */
public interface PredavacService {
    void savePredavac(PredavacDto predavac);
    void updatePredavac(PredavacDto predavac);
    void deletePredavac(Long id);
    PredavacDto findById(Long id);
    List<PredavacDto> findAll();
}
