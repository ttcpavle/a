/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.javaapp0701.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import rs.ac.bg.fon.javaapp0701.repository.PredavacRepository;
import rs.ac.bg.fon.javaapp0701.service.PredavacService;
import rs.ac.bg.fon.javaapp0701.service.impl.PredavacServiceImpl;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import rs.ac.bg.fon.javaapp0701.repository.impl.HibernatePredavacRepository;
import rs.ac.bg.fon.javaapp0701.repository.impl.JDBCPredavacRepository;
import rs.ac.bg.fon.javaapp0701.repository.impl.JPAPredavacRepository;
import rs.ac.bg.fon.javaapp0701.repository.impl.SpringJDBCPredavacRepository;

/**
 *
 * @author totic
 */

@Configuration
@ComponentScan(basePackages = "rs.ac.bg.fon.javaapp0701")
public class AppConfig {

    private static final String PU_NAME = 
        "rs.ac.bg.fon_JavaApp0701_jar_1.0-SNAPSHOTPU";

    // --- Service beans ---

    @Bean(name = "jpaPredavacService")
    public PredavacService getJpaPredavacService(@Qualifier("jpa") PredavacRepository pr) {
        return new PredavacServiceImpl(pr);
    }

    @Bean(name = "springJdbcPredavacService")
    public PredavacService getSpringJdbcPredavacService(@Qualifier("springJdbc") PredavacRepository pr) {
        return new PredavacServiceImpl(pr);
    }

    @Bean(name = "hibernatePredavacService")
    public PredavacService getHibernatePredavacService(@Qualifier("hibernate") PredavacRepository pr) {
        return new PredavacServiceImpl(pr);
    }

    @Bean(name = "jdbcPredavacService")
    public PredavacService getJdbcPredavacService(@Qualifier("jdbc") PredavacRepository pr){
        return new PredavacServiceImpl(pr);
    }
    // --- Infrastructure beans ---
    
    // BITNO: datasource bean koristi springJdbc repozitorijum, entityMangerFactory koristi JPA repozitorijum
    // sessionFactory bean koristi hibernate repozitorijum

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource d = new DriverManagerDataSource();
        d.setUrl("jdbc:mysql://localhost:3306/priprema_njt");
        d.setUsername("root");
        d.setPassword("");
        return d;
    }

    @Bean
    @Primary  // koristi se ovaj kao ima vise kandidata. Ovaj i SessionFactory se gledaju kao isti tip
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory(PU_NAME);
    }

    @Bean
    public SessionFactory sessionFactory(EntityManagerFactory emf) {
        return emf.unwrap(SessionFactory.class);
    }
    
    @Bean
    public Connection connection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/priprema_njt", "root", "");
    }
}
