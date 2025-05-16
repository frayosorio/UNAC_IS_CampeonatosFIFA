package campeonatosfifa.api.core.servicios;

import java.util.List;
import campeonatosfifa.api.dominio.entidades.*;

public interface ICampeonatoServicio {

    public List<Campeonato> listar();

    public Campeonato obtener(int id);

    public List<Campeonato> buscar(String nombre);

    public Campeonato agregar(Campeonato campeonato);

    public Campeonato modificar(Campeonato campeonato);

    public boolean eliminar(int id);

}
