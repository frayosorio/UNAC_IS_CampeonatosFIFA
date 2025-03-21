package campeonatosfifa.api.dominio.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fase")
public class Fase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_fase")
    @GenericGenerator(name = "secuencia_fase", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "fase", unique = true, nullable = false)
    private String fase;

    public Fase() {
    }

    public Fase(int id, String fase) {
        this.id = id;
        this.fase = fase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }
}

