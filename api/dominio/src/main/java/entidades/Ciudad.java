package campeonatosfifa.api.dominio.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ciudad")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_ciudad")
    @GenericGenerator(name = "secuencia_ciudad", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @ManyToOne
    @JoinColumn(name = "idpais", referencedColumnName = "id", nullable = false)
    private Seleccion pais;

    public Ciudad() {
    }

    public Ciudad(int id, String ciudad, Seleccion pais) {
        this.id = id;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Seleccion getPais() {
        return pais;
    }

    public void setPais(Seleccion pais) {
        this.pais = pais;
    }
}

