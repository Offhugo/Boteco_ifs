package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.*;
import br.com.boteco.comanda.model.ConsumoModel;
import br.com.boteco.comanda.repository.ConsumoRepository;
import br.com.boteco.comanda.rest.dto.ConsumoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumoService {

    @Autowired
    private ConsumoRepository consumoRepository;

    /**
     * Obtém um consumo pelo ID.
     *
     * @param id ID do consumo.
     * @return ConsumoDTO representando o consumo encontrado.
     * @throws ObjectNotFoundException Se o consumo não for encontrado.
     */
    @Transactional(readOnly = true)
    public ConsumoDTO obterPorId(Long id) {
        ConsumoModel consumo = consumoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Consumo com ID " + id + " não encontrado."));
        return consumo.toDTO();
    }

    @Transactional(readOnly = true)
    public List<ConsumoDTO> obterTodos(){
        List<ConsumoModel> consumos = consumoRepository.findAll();
        return consumos.stream()
                .map(g -> g.toDTO())
                .collect(Collectors.toList());
    }

    @Transactional
    public ConsumoDTO salvar(ConsumoModel novoConsumo){

        try {
            //Salva o novo consumo na base de dados.
            return consumoRepository.save(novoConsumo).toDTO();

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível salvar o consumo " + novoConsumo.getIdComanda() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar o consumo " + novoConsumo.getIdComanda() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar o consumo " + novoConsumo.getIdComanda() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar o consumo " + novoConsumo.getIdComanda() + ". Falha na conexão com o banco de dados!");
        }
    }

    @Transactional
    public ConsumoDTO atualizar(ConsumoModel consumoExistente){

        try {
            //Atualiza o consumo na base de dados.
            return consumoRepository.save(consumoExistente).toDTO();

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível atualizar o consumo " + consumoExistente.getIdComanda() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível atualizar o consumo " + consumoExistente.getIdComanda() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível atualizar o consumo " + consumoExistente.getIdComanda() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível atualizar o consumo " + consumoExistente.getIdComanda() + ". Falha na conexão com o banco de dados!");
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar o consumo" + consumoExistente.getIdComanda() + ". Não encontrado no banco de dados!");
        }
    }

    @Transactional
    public void deletar(ConsumoModel consumoExistente){

        try {
            //Deletar o consumo na base de dados.
            consumoRepository.delete(consumoExistente);

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível deletar o consumo " + consumoExistente.getIdComanda() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível deletar o consumo " + consumoExistente.getIdComanda() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível deletar o consumo " + consumoExistente.getIdComanda() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível atualizar o deletar " + consumoExistente.getIdComanda() + ". Falha na conexão com o banco de dados!");
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível deletar o consumo" + consumoExistente.getIdComanda() + ". Não encontrado no banco de dados!");
        }
    }
}
