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
public class ZvanjeDto {

    private Long id;
    private String zvanjeSr;
    private String zvanjeEng;
    private Collection<Predavac> predavacCollection;

    public ZvanjeDto(String zvanjeSr, String zvanjeEng, Collection<Predavac> predavacCollection) {
        this.zvanjeSr = zvanjeSr;
        this.zvanjeEng = zvanjeEng;
        this.predavacCollection = predavacCollection;
    }

    
    public ZvanjeDto() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZvanjeSr() {
        return zvanjeSr;
    }

    public void setZvanjeSr(String zvanjeSr) {
        this.zvanjeSr = zvanjeSr;
    }

    public String getZvanjeEng() {
        return zvanjeEng;
    }

    public void setZvanjeEng(String zvanjeEng) {
        this.zvanjeEng = zvanjeEng;
    }

    public Collection<Predavac> getPredavacCollection() {
        return predavacCollection;
    }

    public void setPredavacCollection(Collection<Predavac> predavacCollection) {
        this.predavacCollection = predavacCollection;
    }

    
}
