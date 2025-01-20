package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.FormaDePagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaDePagamentoRepository extends JpaRepository<FormaDePagamentoModel, Long> {

    boolean existsByNome(String nome);
}
