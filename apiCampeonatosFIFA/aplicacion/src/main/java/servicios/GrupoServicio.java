package campeonatosfifa.api.aplicacion.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import campeonatosfifa.api.core.servicios.*;
import campeonatosfifa.api.dominio.entidades.*;
import campeonatosfifa.api.dominio.DTOs.*;
import campeonatosfifa.api.infraestructura.repositorios.*;
import jakarta.persistence.EntityManager;

@Service
public class GrupoServicio implements IGrupoServicio {

    @Autowired
    private EntityManager em;

    private IGrupoRepositorio repositorio;
    private IGrupoPaisRepositorio repositorioPaises;

    public GrupoServicio(IGrupoRepositorio repositorio, IGrupoPaisRepositorio repositorioPaises) {
        this.repositorio = repositorio;
        this.repositorioPaises = repositorioPaises;
    }

    @Override
    public List<Grupo> listar() {
        return repositorio.findAll();
    }

    @Override
    public List<Grupo> listarCampeonato(int idCampeonato) {
        return repositorio.listarCampeonato(idCampeonato);
    }

    @Override
    public Grupo obtener(int id) {
        return repositorio.findById(id).isEmpty() ? null : repositorio.findById(id).get();
    }

    @Override
    public List<Grupo> buscar(String nombre) {
        return repositorio.buscar(nombre);
    }

    @Override
    public Grupo agregar(Grupo grupo) {
        grupo.setId(0);
        return repositorio.save(grupo);
    }

    @Override
    public Grupo modificar(Grupo grupo) {
        if (repositorio.findById(grupo.getId()).isEmpty())
            return null;
        return repositorio.save(grupo);
    }

    @Override
    public boolean eliminar(int id) {
        try {
            repositorio.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // ***** Paises del Grupo *****

    @Override
    public List<GrupoPais> listarPaises(int idGrupo) {
        return repositorioPaises.listarPaises(idGrupo);
    }

    @Override
    public GrupoPais obtenerPais(int idGrupo, int idPais) {
        GrupoPaisId grupoPaisId = new GrupoPaisId(idGrupo, idPais);
        return repositorioPaises.findById(grupoPaisId).isEmpty() ? null : repositorioPaises.findById(grupoPaisId).get();
    }

    @Override
    public GrupoPais agregarPais(GrupoPais grupoPais) {
        return repositorioPaises.save(grupoPais);
    }

    @Override
    public GrupoPais modificarPais(GrupoPais grupoPais) {
        GrupoPaisId grupoPaisId = new GrupoPaisId(
                grupoPais.getGrupo().getId(),
                grupoPais.getPais().getId());
        if (repositorioPaises.findById(grupoPaisId).isEmpty())
            return null;
        return repositorioPaises.save(grupoPais);
    }

    @Override
    public boolean eliminarPais(int idGrupo, int idPais) {
        GrupoPaisId grupoPaisId = new GrupoPaisId(idGrupo, idPais);
        try {
            repositorioPaises.deleteById(grupoPaisId);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // ***** Tabla de Posiciones *****
    public List<TablaPosicionesDto> listarTablaPosiciones(int idGrupo) {
        List<TablaPosicionesDto> tablaPosiciones = em
                .createNativeQuery("SELECT * FROM fObtenerTablaPosiciones(:idGrupoTabla)", TablaPosicionesDto.class)
                .setParameter("idGrupoTabla", idGrupo)
                .getResultList();

        return tablaPosiciones;
    }

}
