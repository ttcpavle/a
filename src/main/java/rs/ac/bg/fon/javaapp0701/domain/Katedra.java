/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.javaapp0701.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author totic
 */
@Entity
@Table(name = "katedra")
@NamedQueries({
    @NamedQuery(name = "Katedra.findAll", query = "SELECT k FROM Katedra k"),
    @NamedQuery(name = "Katedra.findById", query = "SELECT k FROM Katedra k WHERE k.id = :id"),
    @NamedQuery(name = "Katedra.findByNaziv", query = "SELECT k FROM Katedra k WHERE k.naziv = :naziv"),
    @NamedQuery(name = "Katedra.findBySkraceniNaziv", query = "SELECT k FROM Katedra k WHERE k.skraceniNaziv = :skraceniNaziv")})
public class Katedra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "naziv")
    private String naziv;
    @Basic(optional = false)
    @Column(name = "skraceni_naziv")
    private String skraceniNaziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "katedraId")
    private Collection<Predavac> predavacCollection;

    public Katedra() {
    }

    public Katedra(Long id) {
        this.id = id;
    }

    public Katedra(Long id, String naziv, String skraceniNaziv) {
        this.id = id;
        this.naziv = naziv;
        this.skraceniNaziv = skraceniNaziv;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Katedra)) {
            return false;
        }
        Katedra other = (Katedra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.ac.bg.fon.javaapp0701.domain.Katedra[ id=" + id + " ]";
    }
    
}
