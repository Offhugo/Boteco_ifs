package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.ComandaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComandaRepository extends JpaRepository<ComandaModel, Long> {

    boolean existsByIdComanda(long idComanda);
}
