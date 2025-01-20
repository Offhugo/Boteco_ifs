package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.*;
import br.com.boteco.comanda.model.ComandaModel;
import br.com.boteco.comanda.repository.ComandaRepository;
import br.com.boteco.comanda.rest.dto.ComandaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComandaService {

    @Autowired
    private ComandaRepository comandaRepository;

    /**
     * Obtém um comanda pelo ID.
     *
     * @param id ID da comanda.
     * @return ComandaDTO representando a comanda encontrado.
     * @throws ObjectNotFoundException Se a comanda não for encontrado.
     */
    @Transactional(readOnly = true)
    public ComandaDTO obterPorId(Long id) {
        ComandaModel comanda = comandaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Mesa com ID " + id + " não encontrado."));
        return comanda.toDTO();
    }

    @Transactional(readOnly = true)
    public List<ComandaDTO> obterTodos(){
        List<ComandaModel> comandas = comandaRepository.findAll();
        return comandas.stream()
                .map(g -> g.toDTO())
                .collect(Collectors.toList());
    }

    @Transactional
    public ComandaDTO salvar(ComandaModel novaComanda){

        try {

            //Caso ocorra uma tentativa de salvar uma nova comanda  já existente.
            if (comandaRepository.existsByIdComanda(novaComanda.getIdComanda())) {
                throw new ConstraintException("Já existe um comanda com esse CPF " + novaComanda.getIdComanda() + " na base de dados!");
            }

            //Salva o novo comanda na base de dados.
            return comandaRepository.save(novaComanda).toDTO();

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível salvar a comanda " + novaComanda.getIdComanda() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar a comanda " + novaComanda.getIdComanda() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar a comanda " + novaComanda.getIdComanda() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar a comanda " + novaComanda.getIdComanda() + ". Falha na conexão com o banco de dados!");
        }
    }

    @Transactional
    public ComandaDTO atualizar(ComandaModel comandaExistente){

        // PENSAMENTO ERRADO AQUI, MEU OBVIO
        try {
            //Caso ocorra uma tentativa de salvar uma nova comanda já existente.
            if (comandaRepository.existsByIdComanda(comandaExistente.getIdComanda())) {
                throw new ConstraintException("Já existe uma comanda com esse nome " + comandaExistente.getIdComanda() + " na base de dados!");
            }

            //Atualiza o comanda na base de dados.
            return comandaRepository.save(comandaExistente).toDTO();

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível atualizar a comanda " + comandaExistente.getIdComanda() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível atualizar a comanda " + comandaExistente.getIdComanda() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível atualizar a comanda " + comandaExistente.getIdComanda() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível atualizar a comanda " + comandaExistente.getIdComanda() + ". Falha na conexão com o banco de dados!");
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar a comanda" + comandaExistente.getIdComanda() + ". Não encontrado no banco de dados!");
        }
    }

    @Transactional
    public void deletar(ComandaModel comandaExistente){

        try {
            //Caso ocorra uma tentativa de salvar uma nova comanda já existente.
            if (comandaRepository.existsByIdComanda(comandaExistente.getIdComanda())) {
                throw new ConstraintException("Já existe um comanda com esse nome " + comandaExistente.getIdComanda() + " na base de dados!");
            }

            //Deletar o comanda na base de dados.
            comandaRepository.delete(comandaExistente);

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível deletar a comanda " + comandaExistente.getIdComanda() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível deletar a comanda " + comandaExistente.getIdComanda() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível deletar a comanda " + comandaExistente.getIdComanda() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível atualizar a deletar " + comandaExistente.getIdComanda() + ". Falha na conexão com o banco de dados!");
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível deletar a comanda" + comandaExistente.getIdComanda() + ". Não encontrado no banco de dados!");
        }
    }
}
