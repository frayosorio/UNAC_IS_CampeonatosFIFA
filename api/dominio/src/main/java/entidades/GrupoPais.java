package campeonatosfifa.api.dominio.entidades;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name="grupopais")
public class GrupoPais {

    @EmbeddedId
    private GrupoPaisId id;

    @ManyToOne
    @MapsId("idGrupo")
    @JoinColumn(name = "idgrupo", referencedColumnName = "id")
    private Grupo grupo;

    @ManyToOne
    @MapsId("idPais")
    @JoinColumn(name = "idpais", referencedColumnName = "id")
    private Seleccion pais;

    public GrupoPais() {}

    public GrupoPais(Grupo grupo, Seleccion pais) {
        this.id = new GrupoPaisId(grupo.getId(), pais.getId());
        this.grupo = grupo;
        this.pais = pais;
    }

    public GrupoPaisId getId() {
        return id;
    }

    public void setId(GrupoPaisId id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Seleccion getPais() {
        return pais;
    }

    public void setPais(Seleccion pais) {
        this.pais = pais;
    }
}

@Embeddable
class GrupoPaisId implements Serializable {
    private int idGrupo;
    private int idPais;

    public GrupoPaisId() {}

    public GrupoPaisId(int idGrupo, int idPais) {
        this.idGrupo = idGrupo;
        this.idPais = idPais;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }
}

