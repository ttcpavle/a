/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package rs.ac.bg.fon.javaapp0701;

import java.util.Date;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.javaapp0701.config.AppConfig;
import rs.ac.bg.fon.javaapp0701.domain.Katedra;
import rs.ac.bg.fon.javaapp0701.domain.Predavac;
import rs.ac.bg.fon.javaapp0701.domain.Zvanje;
import rs.ac.bg.fon.javaapp0701.dto.KatedraDto;
import rs.ac.bg.fon.javaapp0701.dto.PredavacDto;
import rs.ac.bg.fon.javaapp0701.dto.ZvanjeDto;
import rs.ac.bg.fon.javaapp0701.service.PredavacService;

/**
 *
 * @author totic
 */
@Component
public class JavaApp0701 {

    private final PredavacService hibernatePredavacService;
    private final PredavacService springJdbcPredavacService;
    private final PredavacService jpaPredavacService;
    private final PredavacService jdbcPredavacService;

    public JavaApp0701(
            @Qualifier("jpaPredavacService") PredavacService jpaPredavacService,
            @Qualifier("springJdbcPredavacService") PredavacService springJdbcPredavacService,
            @Qualifier("hibernatePredavacService") PredavacService hibernatePredavacService,
            @Qualifier("jdbcPredavacService") PredavacService jdbcPredavacService) {
        this.jpaPredavacService = jpaPredavacService;
        this.springJdbcPredavacService = springJdbcPredavacService;
        this.hibernatePredavacService = hibernatePredavacService;
        this.jdbcPredavacService = jdbcPredavacService;
    }

    public void savePredavac(PredavacDto predavac, int izbor) {
        switch (izbor) {
            case 1 -> jpaPredavacService.savePredavac(predavac);
            case 2 -> springJdbcPredavacService.savePredavac(predavac);
            case 3 -> hibernatePredavacService.savePredavac(predavac);
            case 4 -> jdbcPredavacService.savePredavac(predavac);
            default -> System.out.println("Greska: nepoznat izbor");
        }
    }
    
    
    public static void main(String[] args) {
        // 1. ulaz korisnika
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesi broj 1 za JPA, 2 za Spring JDBC, 3 za Hibernate ORM, 4 za JDBC");
        String unos = scanner.nextLine();
        int izbor = Integer.parseInt(unos);
        
        // 2. kreiraj app context i instanciraj bean aplikacije
        ApplicationContext ioc = new AnnotationConfigApplicationContext(AppConfig.class);
        JavaApp0701 app = ioc.getBean(JavaApp0701.class); // ioc ga premicuje kao bean jer ima anotaciju component
        
        // 3. kreiraj predavaca za cuvanje
        // These objects represent EXISTING rows in the DB (id = their real PK)
        Katedra k = new Katedra(3L);       // row with id=2 already in DB
        Zvanje z = new Zvanje(2L);         // row with id=1 already in DB

        PredavacDto p = new PredavacDto();
        p.setIme("Ilija2");
        p.setPrezime("Petrovic2");
        p.setDatumRodjenja(new Date());
        p.setKatedraId(k); // just sets the FK reference
        p.setZvanjeId(z); // p.id is null — DB will generate it
        
        // 4. sacuvaj prodavaca
        app.savePredavac(p, izbor);
        
    }    
}
