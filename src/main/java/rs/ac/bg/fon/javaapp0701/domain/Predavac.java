/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.javaapp0701.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author totic
 */
@Entity
@Table(name = "predavac")
@NamedQueries({
    @NamedQuery(name = "Predavac.findAll", query = "SELECT p FROM Predavac p"),
    @NamedQuery(name = "Predavac.findById", query = "SELECT p FROM Predavac p WHERE p.id = :id"),
    @NamedQuery(name = "Predavac.findByIme", query = "SELECT p FROM Predavac p WHERE p.ime = :ime"),
    @NamedQuery(name = "Predavac.findByPrezime", query = "SELECT p FROM Predavac p WHERE p.prezime = :prezime"),
    @NamedQuery(name = "Predavac.findByDatumRodjenja", query = "SELECT p FROM Predavac p WHERE p.datumRodjenja = :datumRodjenja")})
public class Predavac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @Column(name = "prezime")
    private String prezime;
    @Basic(optional = false)
    @Column(name = "datum_rodjenja")
    @Temporal(TemporalType.DATE)
    private Date datumRodjenja;
    @JoinColumn(name = "zvanje_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Zvanje zvanjeId;
    @JoinColumn(name = "katedra_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Katedra katedraId;

    public Predavac() {
    }

    public Predavac(Long id) {
        this.id = id;
    }

    public Predavac(Long id, String ime, String prezime, Date datumRodjenja) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Predavac)) {
            return false;
        }
        Predavac other = (Predavac) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.ac.bg.fon.javaapp0701.domain.Predavac[ id=" + id + " ]";
    }
    
}
