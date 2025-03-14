package campeonatosfifa.api.dominio.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "encuentro")
public class Encuentro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_encuentro")
    @GenericGenerator(name = "secuencia_encuentro", strategy = "increment")
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "idpais1", referencedColumnName = "id")
    private Seleccion pais1;

    @ManyToOne
    @JoinColumn(name = "idpais2", referencedColumnName = "id")
    private Seleccion pais2;

    @ManyToOne
    @JoinColumn(name = "idfase", referencedColumnName = "id")
    private Fase fase;

    @ManyToOne
    @JoinColumn(name = "idcampeonato", referencedColumnName = "id")
    private Campeonato campeonato;

    @ManyToOne
    @JoinColumn(name = "idestadio", referencedColumnName = "id")
    private Estadio estadio;

    @Column(name = "fecha")
    private java.sql.Date fecha;

    @Column(name = "goles1")
    private Integer goles1;

    @Column(name = "goles2")
    private Integer goles2;

    public Encuentro() {
    }

    public Encuentro(int id, Seleccion pais1, Seleccion pais2, Fase fase, Campeonato campeonato, Estadio estadio,
            java.sql.Date fecha, Integer goles1, Integer goles2) {
        this.id = id;
        this.pais1 = pais1;
        this.pais2 = pais2;
        this.fase = fase;
        this.campeonato = campeonato;
        this.estadio = estadio;
        this.fecha = fecha;
        this.goles1 = goles1;
        this.goles2 = goles2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seleccion getPais1() {
        return pais1;
    }

    public void setPais1(Seleccion pais1) {
        this.pais1 = pais1;
    }

    public Seleccion getPais2() {
        return pais2;
    }

    public void setPais2(Seleccion pais2) {
        this.pais2 = pais2;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public Integer getGoles1() {
        return goles1;
    }

    public void setGoles1(Integer goles1) {
        this.goles1 = goles1;
    }

    public Integer getGoles2() {
        return goles2;
    }

    public void setGoles2(Integer goles2) {
        this.goles2 = goles2;
    }
    
}
