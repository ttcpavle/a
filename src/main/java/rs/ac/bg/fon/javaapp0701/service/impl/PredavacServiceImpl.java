/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.javaapp0701.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import rs.ac.bg.fon.javaapp0701.domain.Predavac;
import rs.ac.bg.fon.javaapp0701.dto.PredavacDto;
import rs.ac.bg.fon.javaapp0701.repository.PredavacRepository;
import rs.ac.bg.fon.javaapp0701.service.PredavacService;

/**
 *
 * @author totic
 */
public class PredavacServiceImpl implements PredavacService{

    private final PredavacRepository predavacRepository;
    
    // ovde nema dependency injection jer nije jednoznacno odredjeno koji dependency mi treba
    // imam razlicite implementacije i prosledjujem onu koju izabere korisnik
 
    public PredavacServiceImpl(PredavacRepository pr) {
        this.predavacRepository = pr;
    }
 
    // DTO -> domain helper
    private Predavac toEntity(PredavacDto dto) {
        Predavac p = new Predavac();
        p.setId(dto.getId());
        p.setIme(dto.getIme());
        p.setPrezime(dto.getPrezime());
        p.setDatumRodjenja(dto.getDatumRodjenja());
        p.setZvanjeId(dto.getZvanjeId());
        p.setKatedraId(dto.getKatedraId());
        return p;
    }
 
    // domain -> DTO helper
    private PredavacDto toDto(Predavac p) {
        PredavacDto dto = new PredavacDto();
        dto.setId(p.getId());
        dto.setIme(p.getIme());
        dto.setPrezime(p.getPrezime());
        dto.setDatumRodjenja(p.getDatumRodjenja());
        dto.setZvanjeId(p.getZvanjeId());
        dto.setKatedraId(p.getKatedraId());
        return dto;
    }
 
    @Override
    public void savePredavac(PredavacDto predavac) {
        predavacRepository.save(toEntity(predavac));
    }
 
    @Override
    public void updatePredavac(PredavacDto predavac) {
        predavacRepository.update(toEntity(predavac));
    }
 
    @Override
    public void deletePredavac(Long id) {
        predavacRepository.delete(id);
    }
 
    @Override
    public PredavacDto findById(Long id) {
        Predavac p = predavacRepository.findById(id);
        return p != null ? toDto(p) : null;
    }
 
    // kod kreiranja entity klasa izabarti listu umesto kolekciju da bi se izbegla konverzija
    @Override
    public List<PredavacDto> findAll() {
        return predavacRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
}
