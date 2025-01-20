package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.*;
import br.com.boteco.comanda.model.MesaModel;
import br.com.boteco.comanda.repository.MesaRepository;
import br.com.boteco.comanda.rest.dto.MesaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    /**
     * Obtém um mesa pelo ID.
     *
     * @param id ID da mesa.
     * @return MesaDTO representando a mesa encontrado.
     * @throws ObjectNotFoundException Se a mesa não for encontrado.
     */
    @Transactional(readOnly = true)
    public MesaDTO obterPorId(Long id) {
        MesaModel mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Mesa com ID " + id + " não encontrado."));
        return mesa.toDTO();
    }

    @Transactional(readOnly = true)
    public List<MesaDTO> obterTodos(){
        List<MesaModel> mesas = mesaRepository.findAll();
        return mesas.stream()
                .map(g -> g.toDTO())
                .collect(Collectors.toList());
    }

    @Transactional
    public MesaDTO salvar(MesaModel novaMesa){

        try {
            //Caso ocorra uma tentativa de salvar um novo mesa com um cpf já existente.
            if (mesaRepository.existsByNumero(novaMesa.getNumero())) {
                throw new ConstraintException("Já existe um mesa com esse registro" + novaMesa.toDTO().getNumero() + " na base de dados!");
            }

            //Salva o novo mesa na base de dados.
            return mesaRepository.save(novaMesa).toDTO();

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível salvar a mesa " + novaMesa.getNumero() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar a mesa " + novaMesa.getNumero() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar a mesa " + novaMesa.getNumero() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar a mesa " + novaMesa.getNumero() + ". Falha na conexão com o banco de dados!");
        }
    }

    @Transactional
    public MesaDTO atualizar(MesaModel mesaExistente){

        // PENSAMENTO ERRADO AQUI, MEU OBVIO
        try {
            //Caso ocorra uma tentativa de salvar um novo mesa com um cpf já existente.
            if (mesaRepository.existsByNumero(mesaExistente.getNumero())) {
                throw new ConstraintException("Já existe uma mesa com esse nome " + mesaExistente.getNumero() + " na base de dados!");
            }

            //Atualiza o mesa na base de dados.
            return mesaRepository.save(mesaExistente).toDTO();

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível atualizar a mesa " + mesaExistente.getNumero() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível atualizar a mesa " + mesaExistente.getNumero() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível atualizar a mesa " + mesaExistente.getNumero() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível atualizar a mesa " + mesaExistente.getNumero() + ". Falha na conexão com o banco de dados!");
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar a mesa" + mesaExistente.getNumero() + ". Não encontrado no banco de dados!");
        }
    }

    @Transactional
    public void deletar(MesaModel mesaExistente){

        try {
            //Caso ocorra uma tentativa de salvar um novo mesa com um cpf já existente.
            if (mesaRepository.existsByNumero(mesaExistente.getNumero())) {
                throw new ConstraintException("Já existe um mesa com esse nome " + mesaExistente.getNumero() + " na base de dados!");
            }

            //Deletar o mesa na base de dados.
            mesaRepository.delete(mesaExistente);

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível deletar a mesa " + mesaExistente.getNumero() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível deletar a mesa " + mesaExistente.getNumero() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível deletar a mesa " + mesaExistente.getNumero() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível atualizar a deletar " + mesaExistente.getNumero() + ". Falha na conexão com o banco de dados!");
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível deletar a mesa" + mesaExistente.getNumero() + ". Não encontrado no banco de dados!");
        }
    }
}


