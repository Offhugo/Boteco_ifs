package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.*;
import br.com.boteco.comanda.model.ProdutoModel;
import br.com.boteco.comanda.repository.ProdutoRepository;
import br.com.boteco.comanda.rest.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Obtém um produto pelo ID.
     *
     * @param id ID do produto.
     * @return ProdutoDTO representando o produto encontrado.
     * @throws ObjectNotFoundException Se o produto não for encontrado.
     */
    @Transactional(readOnly = true)
    public ProdutoDTO obterPorId(Long id) {
        ProdutoModel produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto com ID " + id + " não encontrado."));
        return produto.toDTO();
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> obterTodos(){
        List<ProdutoModel> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(g -> g.toDTO())
                .collect(Collectors.toList());
    }

    @Transactional
    public ProdutoDTO salvar(ProdutoModel novoProduto){

        try {
            //Caso ocorra uma tentativa de salvar um novo produto com um cpf já existente.
            if (produtoRepository.existsByNome(novoProduto.getNome())) {
                throw new ConstraintException("Já existe um produto com esse nome " + novoProduto.toDTO().getNome() + " na base de dados!");
            }

            //Salva o novo produto na base de dados.
            return produtoRepository.save(novoProduto).toDTO();

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível salvar o produto " + novoProduto.getNome() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar o produto " + novoProduto.getNome() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar o produto " + novoProduto.getNome() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar o produto " + novoProduto.getNome() + ". Falha na conexão com o banco de dados!");
        }
    }

    @Transactional
    public ProdutoDTO atualizar(ProdutoModel produtoExistente){

        // PENSAMENTO ERRADO AQUI, MEU OBVIO
        try {
            //Caso ocorra uma tentativa de salvar um novo produto com um cpf já existente.
            if (produtoRepository.existsByNome(produtoExistente.getNome())) {
                throw new ConstraintException("Já existe um produto com esse nome " + produtoExistente.getNome() + " na base de dados!");
            }

            //Atualiza o produto na base de dados.
            return produtoRepository.save(produtoExistente).toDTO();

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível atualizar o produto " + produtoExistente.getNome() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível atualizar o produto " + produtoExistente.getNome() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível atualizar o produto " + produtoExistente.getNome() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível atualizar o produto " + produtoExistente.getNome() + ". Falha na conexão com o banco de dados!");
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar o produto" + produtoExistente.getNome() + ". Não encontrado no banco de dados!");
        }
    }

    @Transactional
    public void deletar(ProdutoModel produtoExistente){

        try {
            //Caso ocorra uma tentativa de salvar um novo produto com um cpf já existente.
            if (produtoRepository.existsByNome(produtoExistente.getNome())) {
                throw new ConstraintException("Já existe um produto com esse nome " + produtoExistente.getNome() + " na base de dados!");
            }

            //Deletar o produto na base de dados.
            produtoRepository.delete(produtoExistente);

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro! Não foi possível deletar o produto " + produtoExistente.getNome() + " !");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível deletar o produto " + produtoExistente.getNome() + ". Violação de integridade de dados!");
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível deletar o produto " + produtoExistente.getNome() + ". Violação de regra de negócio!");
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível atualizar o deletar " + produtoExistente.getNome() + ". Falha na conexão com o banco de dados!");
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível deletar o produto" + produtoExistente.getNome() + ". Não encontrado no banco de dados!");
        }
    }
}

