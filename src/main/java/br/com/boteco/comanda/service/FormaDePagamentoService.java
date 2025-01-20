package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.*;
import br.com.boteco.comanda.model.FormaDePagamentoModel;
import br.com.boteco.comanda.repository.FormaDePagamentoRepository;
import br.com.boteco.comanda.rest.dto.FormaDePagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormaDePagamentoService {

    @Autowired
    private FormaDePagamentoRepository formaDePagamentoRepository;

    /**
     * Obtém um forma de pagamento pelo ID.
     *
     * @param id ID da forma de pagamento.
     * @return FormaDePagamentoDTO representando a forma de pagamento encontrado.
     * @throws ObjectNotFoundException Se a forma de pagamento não for encontrado.
     */
    @Transactional(readOnly = true)
    public FormaDePagamentoDTO obterPorId(Long id) {
        FormaDePagamentoModel formaDePagamento = formaDePagamentoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Forma de pagamento com ID " + id + " não encontrado."));
        return formaDePagamento.toDTO();
    }

    @Transactional(readOnly = true)
    public List<FormaDePagamentoDTO> obterTodos(){
        List<FormaDePagamentoModel> formasDePagamento = formaDePagamentoRepository.findAll();
        return formasDePagamento.stream()
                .map(g -> g.toDTO())
                .collect(Collectors.toList());
    }

    @Transactional
    public FormaDePagamentoDTO salvar(FormaDePagamentoModel novaFormaDePagamento){

        try {
            //Caso ocorra uma tentativa de salvar um novo forma de pagamento com um nome já existente.
            if (formaDePagamentoRepository.existsByNome(novaFormaDePagamento.getNome())) {
                throw new ConstraintException("Já existe uma forma de pagamento registrada " + novaFormaDePagamento.toDTO().getNome() + " na base de dados!");
            }

            //Salva o novo forma de pagamento na base de dados.
            return formaDePagamentoRepository.save(novaFormaDePagamento).toDTO();

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível salvar a forma de pagamento " + novaFormaDePagamento.getNome() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar a forma de pagamento " + novaFormaDePagamento.getNome() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar a forma de pagamento " + novaFormaDePagamento.getNome() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar a forma de pagamento " + novaFormaDePagamento.getNome() + ". Falha na conexão com o banco de dados!");
        }
    }

    @Transactional
    public FormaDePagamentoDTO atualizar(FormaDePagamentoModel formaDePagamentoExistente){

        // PENSAMENTO ERRADO AQUI, MEU OBVIO
        try {
            //Caso ocorra uma tentativa de salvar um novo forma de pagamento com um nome já existente.
            if (formaDePagamentoRepository.existsByNome(formaDePagamentoExistente.getNome())) {
                throw new ConstraintException("Já existe uma forma de pagamento registrada " + formaDePagamentoExistente.getNome() + " na base de dados!");
            }

            //Atualiza o forma de pagamento na base de dados.
            return formaDePagamentoRepository.save(formaDePagamentoExistente).toDTO();

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível atualizar a forma de pagamento " + formaDePagamentoExistente.getNome() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível atualizar a forma de pagamento " + formaDePagamentoExistente.getNome() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível atualizar a forma de pagamento " + formaDePagamentoExistente.getNome() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível atualizar a forma de pagamento " + formaDePagamentoExistente.getNome() + ". Falha na conexão com o banco de dados!");
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar a forma de pagamento" + formaDePagamentoExistente.getNome() + ". Não encontrado no banco de dados!");
        }
    }

    @Transactional
    public void deletar(FormaDePagamentoModel formaDePagamentoExistente){

        try {
            //Caso ocorra uma tentativa de salvar um novo forma de pagamento com um nome já existente.
            if (formaDePagamentoRepository.existsByNome(formaDePagamentoExistente.getNome())) {
                throw new ConstraintException("Já existe um forma de pagamento com esse nome " + formaDePagamentoExistente.getNome() + " na base de dados!");
            }

            //Deletar o forma de pagamento na base de dados.
            formaDePagamentoRepository.delete(formaDePagamentoExistente);

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível deletar a forma de pagamento " + formaDePagamentoExistente.getNome() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível deletar a forma de pagamento " + formaDePagamentoExistente.getNome() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível deletar a forma de pagamento " + formaDePagamentoExistente.getNome() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível atualizar a deletar " + formaDePagamentoExistente.getNome() + ". Falha na conexão com o banco de dados!");
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível deletar a forma de pagamento" + formaDePagamentoExistente.getNome() + ". Não encontrado no banco de dados!");
        }
    }
}



