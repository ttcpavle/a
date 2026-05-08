/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.javaapp0701.dto;

import java.util.Collection;
import rs.ac.bg.fon.javaapp0701.domain.Predavac;

/**
 *
 * @author totic
 */
public class KatedraDto {

    private Long id;
    private String naziv;
    private String skraceniNaziv;
    private Collection<Predavac> predavacCollection;

    public KatedraDto() {
    }

    public KatedraDto(String naziv, String skraceniNaziv, Collection<Predavac> predavacCollection) {
        this.naziv = naziv;
        this.skraceniNaziv = skraceniNaziv;
        this.predavacCollection = predavacCollection;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSkraceniNaziv() {
        return skraceniNaziv;
    }

    public void setSkraceniNaziv(String skraceniNaziv) {
        this.skraceniNaziv = skraceniNaziv;
    }

    public Collection<Predavac> getPredavacCollection() {
        return predavacCollection;
    }

    public void setPredavacCollection(Collection<Predavac> predavacCollection) {
        this.predavacCollection = predavacCollection;
    }
    
    
}
