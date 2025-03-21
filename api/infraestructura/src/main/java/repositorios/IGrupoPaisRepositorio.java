package campeonatosfifa.api.infraestructura.repositorios;

import campeonatosfifa.api.dominio.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGrupoPaisRepositorio extends JpaRepository<GrupoPais, GrupoPaisId>{

}