/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.javaapp0701.repository.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.javaapp0701.domain.Katedra;
import rs.ac.bg.fon.javaapp0701.domain.Predavac;
import rs.ac.bg.fon.javaapp0701.domain.Zvanje;
import rs.ac.bg.fon.javaapp0701.repository.PredavacRepository;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author totic
 */
@Repository(value="springJdbc")
public class SpringJDBCPredavacRepository implements PredavacRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringJDBCPredavacRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    
    // da bi prebacili resultset u objekat klase Predavac mozemo koristiti RowMapper
    // interface iz org.springframework.jdbc.core.RowMapper
    private final RowMapper<Predavac> predavacRowMapper = (rs, rowNum) -> {
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
    };
 
    private static final String SELECT_SQL =
        "SELECT p.id, p.ime, p.prezime, p.datum_rodjenja, " +
        "p.zvanje_id, z.zvanje_sr, z.zvanje_eng, " +
        "p.katedra_id, k.naziv, k.skraceni_naziv " +
        "FROM predavac p " +
        "JOIN zvanje z ON p.zvanje_id = z.id " +
        "JOIN katedra k ON p.katedra_id = k.id";
 
 
    @Override
    public void save(Predavac predavac) {
        jdbcTemplate.update(
            "INSERT INTO predavac (ime, prezime, datum_rodjenja, zvanje_id, katedra_id) VALUES (?,?,?,?,?)",
            predavac.getIme(),
            predavac.getPrezime(),
            new java.sql.Date(predavac.getDatumRodjenja().getTime()),
            predavac.getZvanjeId().getId(),
            predavac.getKatedraId().getId()
        );
    }
 
    @Override
    public void update(Predavac predavac) {
        jdbcTemplate.update(
            "UPDATE predavac SET ime=?, prezime=?, datum_rodjenja=?, zvanje_id=?, katedra_id=? WHERE id=?",
            predavac.getIme(),
            predavac.getPrezime(),
            new java.sql.Date(predavac.getDatumRodjenja().getTime()),
            predavac.getZvanjeId().getId(),
            predavac.getKatedraId().getId(),
            predavac.getId()
        );
    }
 
    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM predavac WHERE id=?", id);
    }
 
    @Override
    public Predavac findById(Long id) {
        List<Predavac> result = jdbcTemplate.query(
            SELECT_SQL + " WHERE p.id = ?",
            predavacRowMapper,
            id
        );
        return result.isEmpty() ? null : result.get(0);
    }
 
    @Override
    public List<Predavac> findAll() {
        return jdbcTemplate.query(SELECT_SQL, predavacRowMapper);
    }
}
