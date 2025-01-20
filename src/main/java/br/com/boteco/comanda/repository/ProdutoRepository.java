package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

    boolean existsByNome(String nome);
}
