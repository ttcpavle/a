/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.javaapp0701.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.javaapp0701.domain.Katedra;
import rs.ac.bg.fon.javaapp0701.domain.Predavac;
import rs.ac.bg.fon.javaapp0701.domain.Zvanje;
import rs.ac.bg.fon.javaapp0701.repository.PredavacRepository;

/**
 *
 * @author totic
 */
@Repository(value="jdbc")
public class JDBCPredavacRepository implements PredavacRepository{
    
    private final Connection connection;
 
    @Autowired
    public JDBCPredavacRepository(Connection connection) {
        this.connection = connection;
    }
 
    @Override
    public void save(Predavac predavac) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO predavac (ime, prezime, datum_rodjenja, zvanje_id, katedra_id) VALUES (?,?,?,?,?)");
            ps.setString(1, predavac.getIme());
            ps.setString(2, predavac.getPrezime());
            ps.setDate(3, new java.sql.Date(predavac.getDatumRodjenja().getTime()));
            ps.setLong(4, predavac.getZvanjeId().getId());
            ps.setLong(5, predavac.getKatedraId().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(JDBCPredavacRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
 
    @Override
    public void update(Predavac predavac) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                "UPDATE predavac SET ime=?, prezime=?, datum_rodjenja=?, zvanje_id=?, katedra_id=? WHERE id=?");
            ps.setString(1, predavac.getIme());
            ps.setString(2, predavac.getPrezime());
            ps.setDate(3, new java.sql.Date(predavac.getDatumRodjenja().getTime()));
            ps.setLong(4, predavac.getZvanjeId().getId());
            ps.setLong(5, predavac.getKatedraId().getId());
            ps.setLong(6, predavac.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(JDBCPredavacRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
 
    @Override
    public void delete(Long id) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM predavac WHERE id=?");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(JDBCPredavacRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
 
    @Override
    public Predavac findById(Long id) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                "SELECT p.id, p.ime, p.prezime, p.datum_rodjenja, " +
                "p.zvanje_id, z.zvanje_sr, z.zvanje_eng, " +
                "p.katedra_id, k.naziv, k.skraceni_naziv " +
                "FROM predavac p " +
                "JOIN zvanje z ON p.zvanje_id = z.id " +
                "JOIN katedra k ON p.katedra_id = k.id " +
                "WHERE p.id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException ex) {
            System.getLogger(JDBCPredavacRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
 
    @Override
    public List<Predavac> findAll() {
        List<Predavac> lista = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                "SELECT p.id, p.ime, p.prezime, p.datum_rodjenja, " +
                "p.zvanje_id, z.zvanje_sr, z.zvanje_eng, " +
                "p.katedra_id, k.naziv, k.skraceni_naziv " +
                "FROM predavac p " +
                "JOIN zvanje z ON p.zvanje_id = z.id " +
                "JOIN katedra k ON p.katedra_id = k.id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        } catch (SQLException ex) {
            System.getLogger(JDBCPredavacRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return lista;
    }
 
    // maps a ResultSet row to a Predavac object
    private Predavac mapRow(ResultSet rs) throws SQLException {
        Zvanje z = new Zvanje();
        z.setId(rs.getLong("zvanje_id"));
        z.setZvanjeSr(rs.getString("zvanje_sr"));
        z.setZvanjeEng(rs.getString("zvanje_eng"));
 
        Katedra k = new Katedra();
        k.setId(rs.getLong("katedra_id"));
        k.setNaziv(rs.getString("naziv"));
        k.setSkraceniNaziv(rs.getString("skraceni_naziv"));
 
        Predavac p = new Predavac();
        p.setId(rs.getLong("id"));
        p.setIme(rs.getString("ime"));
        p.setPrezime(rs.getString("prezime"));
        p.setDatumRodjenja(rs.getDate("datum_rodjenja"));
        p.setZvanjeId(z);
        p.setKatedraId(k);
        return p;
    }
    
    
}
