package campeonatosfifa.api.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pais")
public class Seleccion {

    @Column(name="id")
    private int id;

}
