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
@Table(name = "zvanje")
@NamedQueries({
    @NamedQuery(name = "Zvanje.findAll", query = "SELECT z FROM Zvanje z"),
    @NamedQuery(name = "Zvanje.findById", query = "SELECT z FROM Zvanje z WHERE z.id = :id"),
    @NamedQuery(name = "Zvanje.findByZvanjeSr", query = "SELECT z FROM Zvanje z WHERE z.zvanjeSr = :zvanjeSr"),
    @NamedQuery(name = "Zvanje.findByZvanjeEng", query = "SELECT z FROM Zvanje z WHERE z.zvanjeEng = :zvanjeEng")})
public class Zvanje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "zvanje_sr")
    private String zvanjeSr;
    @Basic(optional = false)
    @Column(name = "zvanje_eng")
    private String zvanjeEng;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zvanjeId")
    private Collection<Predavac> predavacCollection;

    public Zvanje() {
    }

    public Zvanje(Long id) {
        this.id = id;
    }

    public Zvanje(Long id, String zvanjeSr, String zvanjeEng) {
        this.id = id;
        this.zvanjeSr = zvanjeSr;
        this.zvanjeEng = zvanjeEng;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zvanje)) {
            return false;
        }
        Zvanje other = (Zvanje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.ac.bg.fon.javaapp0701.domain.Zvanje[ id=" + id + " ]";
    }
    
}
