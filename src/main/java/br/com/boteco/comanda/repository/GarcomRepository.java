package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.GarcomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarcomRepository extends JpaRepository<GarcomModel, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
