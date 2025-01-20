package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.MesaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<MesaModel, Long> {

   boolean existsByNumero(Integer numero);
}