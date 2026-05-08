# JavaApp0701 – Kompletan kod projekta

---
Kreiramo bazu podataka sa tabelama i maven projekat
## 1. `pom.xml`
Bitan je samo dependency
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>rs.ac.bg.fon</groupId>
    <artifactId>JavaApp0701</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.release>17</maven.compiler.release>
        <exec.mainClass>rs.ac.bg.fon.javaapp0701.JavaApp0701</exec.mainClass>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>7.0.7</version>
            <scope>compile</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>7.0.7</version>
            <scope>compile</scope>
        </dependency>

        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>9.7.0</version>
            <scope>compile</scope>
        </dependency>

        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>6.5.2.Final</version>
        </dependency>
    </dependencies>
</project>
```

---

## 2. `persistence.xml`
Automatski ce se generisati ako koristimo `Entity class from database`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
  <persistence-unit name="rs.ac.bg.fon_JavaApp0701_jar_1.0-SNAPSHOTPU" transaction-type="RESOURCE_LOCAL">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <class>rs.ac.bg.fon.javaapp0701.domain.Predavac</class>
    <class>rs.ac.bg.fon.javaapp0701.domain.Zvanje</class>
    <class>rs.ac.bg.fon.javaapp0701.domain.Katedra</class>
    <properties>
      <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/priprema_njt?zeroDateTimeBehavior=CONVERT_TO_NULL"/>
      <property name="javax.persistence.jdbc.user" value="root"/>
      <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
      <property name="javax.persistence.jdbc.password" value=""/>
    </properties>
  </persistence-unit>
</persistence>
```

---

## 3. `domain/Zvanje.java`
koristimo `Entity class from database`. Preimenujemo ako moze zvanjeId u zvanje.
```java
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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getZvanjeSr() { return zvanjeSr; }
    public void setZvanjeSr(String zvanjeSr) { this.zvanjeSr = zvanjeSr; }
    public String getZvanjeEng() { return zvanjeEng; }
    public void setZvanjeEng(String zvanjeEng) { this.zvanjeEng = zvanjeEng; }
    public Collection<Predavac> getPredavacCollection() { return predavacCollection; }
    public void setPredavacCollection(Collection<Predavac> predavacCollection) { this.predavacCollection = predavacCollection; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
```

---

## 4. `domain/Katedra.java`

```java
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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    public String getSkraceniNaziv() { return skraceniNaziv; }
    public void setSkraceniNaziv(String skraceniNaziv) { this.skraceniNaziv = skraceniNaziv; }
    public Collection<Predavac> getPredavacCollection() { return predavacCollection; }
    public void setPredavacCollection(Collection<Predavac> predavacCollection) { this.predavacCollection = predavacCollection; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
```

---

## 5. `domain/Predavac.java`

```java
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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }
    public Date getDatumRodjenja() { return datumRodjenja; }
    public void setDatumRodjenja(Date datumRodjenja) { this.datumRodjenja = datumRodjenja; }
    public Zvanje getZvanjeId() { return zvanjeId; }
    public void setZvanjeId(Zvanje zvanjeId) { this.zvanjeId = zvanjeId; }
    public Katedra getKatedraId() { return katedraId; }
    public void setKatedraId(Katedra katedraId) { this.katedraId = katedraId; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
```

---

## 6. `dto/ZvanjeDto.java`
Kreiramo sve dto objekte
```java
public class ZvanjeDto {

    private Long id;
    private String zvanjeSr;
    private String zvanjeEng;
    private Collection<Predavac> predavacCollection;

    public ZvanjeDto() {
    }

    public ZvanjeDto(String zvanjeSr, String zvanjeEng, Collection<Predavac> predavacCollection) {
        this.zvanjeSr = zvanjeSr;
        this.zvanjeEng = zvanjeEng;
        this.predavacCollection = predavacCollection;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getZvanjeSr() { return zvanjeSr; }
    public void setZvanjeSr(String zvanjeSr) { this.zvanjeSr = zvanjeSr; }
    public String getZvanjeEng() { return zvanjeEng; }
    public void setZvanjeEng(String zvanjeEng) { this.zvanjeEng = zvanjeEng; }
    public Collection<Predavac> getPredavacCollection() { return predavacCollection; }
    public void setPredavacCollection(Collection<Predavac> predavacCollection) { this.predavacCollection = predavacCollection; }
}
```

---

## 7. `dto/KatedraDto.java`

```java
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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    public String getSkraceniNaziv() { return skraceniNaziv; }
    public void setSkraceniNaziv(String skraceniNaziv) { this.skraceniNaziv = skraceniNaziv; }
    public Collection<Predavac> getPredavacCollection() { return predavacCollection; }
    public void setPredavacCollection(Collection<Predavac> predavacCollection) { this.predavacCollection = predavacCollection; }
}
```

---

## 8. `dto/PredavacDto.java`

```java
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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }
    public Date getDatumRodjenja() { return datumRodjenja; }
    public void setDatumRodjenja(Date datumRodjenja) { this.datumRodjenja = datumRodjenja; }
    public Zvanje getZvanjeId() { return zvanjeId; }
    public void setZvanjeId(Zvanje zvanjeId) { this.zvanjeId = zvanjeId; }
    public Katedra getKatedraId() { return katedraId; }
    public void setKatedraId(Katedra katedraId) { this.katedraId = katedraId; }
}
```

---

## 9. `repository/PredavacRepository.java`
Prvo interface
```java
public interface PredavacRepository {
    void save(Predavac predavac);
    void update(Predavac predavac);
    void delete(Long id);
    Predavac findById(Long id);
    List<Predavac> findAll();
}
```

---
Potom implementacije
## 10. `repository/impl/JDBCPredavacRepository.java`

```java
@Repository(value="jdbc")
public class JDBCPredavacRepository implements PredavacRepository {

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
```

---

## 11. `repository/impl/SpringJDBCPredavacRepository.java`

```java
@Repository(value="springJdbc")
public class SpringJDBCPredavacRepository implements PredavacRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringJDBCPredavacRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

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
```

---

## 12. `repository/impl/JPAPredavacRepository.java`

```java
@Repository(value="jpa")
public class JPAPredavacRepository implements PredavacRepository {

    private final EntityManagerFactory emf;

    public JPAPredavacRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(Predavac predavac) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(predavac);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Predavac predavac) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(predavac);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Predavac p = em.find(Predavac.class, id);
            if (p != null) {
                em.remove(p);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Predavac findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Predavac.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Predavac> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Predavac.findAll", Predavac.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}
```

---

## 13. `repository/impl/HibernatePredavacRepository.java`

```java
@Repository(value="hibernate")
public class HibernatePredavacRepository implements PredavacRepository {

    private final SessionFactory sessionFactory;

    public HibernatePredavacRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Predavac predavac) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                session.persist(predavac);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(Predavac predavac) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                session.merge(predavac);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                Predavac p = session.get(Predavac.class, id);
                if (p != null) {
                    session.remove(p);
                }
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    @Override
    public Predavac findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Predavac.class, id);
        }
    }

    @Override
    public List<Predavac> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createNamedQuery("Predavac.findAll", Predavac.class)
                          .getResultList();
        }
    }
}
```

---

## 14. `service/PredavacService.java`

```java
public interface PredavacService {
    void savePredavac(PredavacDto predavac);
    void updatePredavac(PredavacDto predavac);
    void deletePredavac(Long id);
    PredavacDto findById(Long id);
    List<PredavacDto> findAll();
}
```

---

## 15. `service/impl/PredavacServiceImpl.java`

```java
public class PredavacServiceImpl implements PredavacService {

    private final PredavacRepository predavacRepository;

    public PredavacServiceImpl(PredavacRepository pr) {
        this.predavacRepository = pr;
    }

    private Predavac toEntity(PredavacDto dto) {
        Predavac p = new Predavac();
        p.setId(dto.getId());
        p.setIme(dto.getIme());
        p.setPrezime(dto.getPrezime());
        p.setDatumRodjenja(dto.getDatumRodjenja());
        p.setZvanjeId(dto.getZvanjeId());
        p.setKatedraId(dto.getKatedraId());
        return p;
    }

    private PredavacDto toDto(Predavac p) {
        PredavacDto dto = new PredavacDto();
        dto.setId(p.getId());
        dto.setIme(p.getIme());
        dto.setPrezime(p.getPrezime());
        dto.setDatumRodjenja(p.getDatumRodjenja());
        dto.setZvanjeId(p.getZvanjeId());
        dto.setKatedraId(p.getKatedraId());
        return dto;
    }

    @Override
    public void savePredavac(PredavacDto predavac) {
        predavacRepository.save(toEntity(predavac));
    }

    @Override
    public void updatePredavac(PredavacDto predavac) {
        predavacRepository.update(toEntity(predavac));
    }

    @Override
    public void deletePredavac(Long id) {
        predavacRepository.delete(id);
    }

    @Override
    public PredavacDto findById(Long id) {
        Predavac p = predavacRepository.findById(id);
        return p != null ? toDto(p) : null;
    }

    @Override
    public List<PredavacDto> findAll() {
        return predavacRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
```

---

## 16. `config/AppConfig.java`

```java
@Configuration
@ComponentScan(basePackages = "rs.ac.bg.fon.javaapp0701")
public class AppConfig {

    private static final String PU_NAME =
        "rs.ac.bg.fon_JavaApp0701_jar_1.0-SNAPSHOTPU";

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
    public PredavacService getJdbcPredavacService(@Qualifier("jdbc") PredavacRepository pr) {
        return new PredavacServiceImpl(pr);
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource d = new DriverManagerDataSource();
        d.setUrl("jdbc:mysql://localhost:3306/priprema_njt");
        d.setUsername("root");
        d.setPassword("");
        return d;
    }

    @Bean
    @Primary
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory(PU_NAME);
    }

    @Bean
    public SessionFactory sessionFactory(EntityManagerFactory emf) {
        return emf.unwrap(SessionFactory.class);
    }

    @Bean
    public Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/priprema_njt", "root", "");
    }
}
```

---

## 17. `JavaApp0701.java`

```java
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesi broj 1 za JPA, 2 za Spring JDBC, 3 za Hibernate ORM, 4 za JDBC");
        String unos = scanner.nextLine();
        int izbor = Integer.parseInt(unos);

        ApplicationContext ioc = new AnnotationConfigApplicationContext(AppConfig.class);
        JavaApp0701 app = ioc.getBean(JavaApp0701.class);

        Katedra k = new Katedra(3L);
        Zvanje z = new Zvanje(2L);

        PredavacDto p = new PredavacDto();
        p.setIme("Ilija");
        p.setPrezime("Petrovic");
        p.setDatumRodjenja(new Date());
        p.setKatedraId(k);
        p.setZvanjeId(z);

        app.savePredavac(p, izbor);
    }
}
```

## Ceste greske
- Switch bez break → svi case-ovi se izvršavaju (fall-through)
- (Date) cast umesto new java.sql.Date(utilDate.getTime()) → ClassCastException
- Zamenjeni redosled parametara u PreparedStatement → INSERT u pogrešne kolone
- Typo u bean imenu u AppConfig vs @Qualifier → NoSuchBeanDefinitionException
- Nedostaje @Primary kada postoje 2 beana koja implementiraju isti interfejs → greška pri pokretanju
- delete() direktno na detached objektu u JPA/Hibernate → mora prvo find()/get()
- na skolskim racunarima maven ne radi u student useru, mora na boljoj lokaciji.
- Preimenovati zvanjeId u zvanje zbog prakticnosti
- los import za npr Session
