/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.javaapp0701.dto;

import java.util.Date;
import rs.ac.bg.fon.javaapp0701.domain.Katedra;
import rs.ac.bg.fon.javaapp0701.domain.Zvanje;

/**
 *
 * @author totic
 */
public class PredavacDto {

    private Long id;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private Zvanje zvanjeId;
    private Katedra katedraId;

    public PredavacDto() {
    }

    public PredavacDto(String ime, String prezime, Date datumRodjenja, Zvanje zvanjeId, Katedra katedraId) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.zvanjeId = zvanjeId;
        this.katedraId = katedraId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Zvanje getZvanjeId() {
        return zvanjeId;
    }

    public void setZvanjeId(Zvanje zvanjeId) {
        this.zvanjeId = zvanjeId;
    }

    public Katedra getKatedraId() {
        return katedraId;
    }

    public void setKatedraId(Katedra katedraId) {
        this.katedraId = katedraId;
    }
    
    
}
