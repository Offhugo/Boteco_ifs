package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.*;
import br.com.boteco.comanda.model.GarcomModel;
import br.com.boteco.comanda.repository.GarcomRepository;
import br.com.boteco.comanda.rest.dto.GarcomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GarcomService {

    @Autowired
    private GarcomRepository garcomRepository;

    /**
     * Obtém um garçom pelo ID.
     *
     * @param id ID do garçom.
     * @return GarcomDTO representando o garçom encontrado.
     * @throws ObjectNotFoundException Se o garçom não for encontrado.
     */
    @Transactional(readOnly = true)
    public GarcomDTO obterPorId(Long id) {
        GarcomModel garcom = garcomRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Garçom com ID " + id + " não encontrado."));
        return garcom.toDTO();
    }

    @Transactional(readOnly = true)
    public List<GarcomDTO> obterTodos(){
        List<GarcomModel> garcons = garcomRepository.findAll();
        return garcons.stream()
                .map(g -> g.toDTO())
                .collect(Collectors.toList());
    }

    @Transactional
    public GarcomDTO salvar(GarcomModel novoGarcom){

        try {
            //Caso ocorra uma tentativa de salvar um novo garçom com um cpf já existente.
            if (garcomRepository.existsByCpf(novoGarcom.getCpf())) {
                throw new ConstraintException("Já existe um garçom com esse CPF " + novoGarcom.getCpf() + " na base de dados!");
            }

            //Caso ocorra uma tentativa de salvar um novo garçom com um e-mail já existente.
            if (garcomRepository.existsByEmail(novoGarcom.getEmail())) {
                throw new ConstraintException("Já existe um garçom com esse E-MAIL " + novoGarcom.getEmail() + " na base de dados!");
            }

            //Salva o novo garçom na base de dados.
            return garcomRepository.save(novoGarcom).toDTO();

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível salvar o garçom " + novoGarcom.getNome() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar o garçom " + novoGarcom.getNome() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar o garçom " + novoGarcom.getNome() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar o garçom " + novoGarcom.getNome() + ". Falha na conexão com o banco de dados!");
        }
    }

    @Transactional
    public GarcomDTO atualizar(GarcomModel garcomExistente){

        try {
            //Caso ocorra uma tentativa de salvar um garçom que não existe utilizando um cpf.
            if (!garcomRepository.existsByCpf(garcomExistente.getCpf())) {
                throw new ConstraintException("O garçom com esse CPF " + garcomExistente.getCpf() + " não existe na base de dados!");
            }

            //Atualiza o garçom na base de dados.
            return garcomRepository.save(garcomExistente).toDTO();

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível atualizar o garçom " + garcomExistente.getNome() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível atualizar o garçom " + garcomExistente.getNome() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível atualizar o garçom " + garcomExistente.getNome() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível atualizar o garçom " + garcomExistente.getNome() + ". Falha na conexão com o banco de dados!");
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar o garçom" + garcomExistente.getNome() + ". Não encontrado no banco de dados!");
        }
    }

    @Transactional
    public void deletar(GarcomModel garcomExistente){

        try {
            //Caso ocorra uma tentativa de salvar um garçom que não existe utilizando um cpf.
            if (!garcomRepository.existsByCpf(garcomExistente.getCpf())) {
                throw new ConstraintException("O garçom com esse CPF " + garcomExistente.getCpf() + " não existe na base de dados!");
            }

            //Deletar o garçom na base de dados.
            garcomRepository.delete(garcomExistente);

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível deletar o garçom " + garcomExistente.getNome() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível deletar o garçom " + garcomExistente.getNome() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível deletar o garçom " + garcomExistente.getNome() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível atualizar o deletar " + garcomExistente.getNome() + ". Falha na conexão com o banco de dados!");
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível deletar o garçom" + garcomExistente.getNome() + ". Não encontrado no banco de dados!");
        }
    }
}
