package campeonatosfifa.api.dominio.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "fase")
public class Fase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_fase")
    @GenericGenerator(name = "secuencia_fase", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "fase", length = 50, unique = true)
    private String nombre;

    public Fase() {
    }

    public Fase(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
