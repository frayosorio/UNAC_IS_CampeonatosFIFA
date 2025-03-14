package campeonatosfifa.api.dominio.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "estadio")
public class Estadio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_estadio")
    @GenericGenerator(name = "secuencia_estadio", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "estadio", length = 50)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idciudad", referencedColumnName = "id")
    private Ciudad ciudad;

    @Column(name = "capacidad")
    private int capacidad;

    public Estadio() {
    }

    public Estadio(int id, String nombre, Ciudad ciudad, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.capacidad = capacidad;
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

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
