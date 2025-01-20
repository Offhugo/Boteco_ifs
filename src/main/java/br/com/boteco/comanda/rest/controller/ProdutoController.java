package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.ProdutoModel;
import br.com.boteco.comanda.rest.dto.ProdutoDTO;
import br.com.boteco.comanda.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável por gerenciar as operações relacionadas aos produtos.
 */
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    /**
     * Instância do serviço de produtos, responsável por encapsular a lógica de negócios
     * e intermediar as operações entre o controlador e o repositório.
     */
    @Autowired
    private ProdutoService produtoService;

    /**
     * Obtém a lista de todos os produtos cadastrados.
     *
     * @return Lista de ProdutoDTO representando os produtos cadastrados.
     */
    @GetMapping()
    public ResponseEntity<List<ProdutoDTO>> obterTodos() {
        List<ProdutoDTO> produtoDTOS = produtoService.obterTodos();
        return ResponseEntity.ok(produtoDTOS);
    }

    /**
     * Obtém um produto pelo ID.
     *
     * @param id ID do produto.
     * @return ProdutoDTO representando o produto encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> obterPorId(@PathVariable Long id) {
        ProdutoDTO produtoDTO = produtoService.obterPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(produtoDTO);
    }

    /**
     * Salva um novo produto na base de dados.
     *
     * @param novoProduto ProdutoModel contendo os dados do novo produto.
     * @return ProdutoDTO representando o produto salvo.
     */
    @PostMapping
    public ResponseEntity<ProdutoDTO> salvar(@Valid @RequestBody ProdutoModel novoProduto) {
        ProdutoDTO novoProdutoDTO = produtoService.salvar(novoProduto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProdutoDTO);
    }

    /**
     * Atualiza os dados de um produto existente.
     *
     * @param produtoExistente ProdutoModel contendo os dados atualizados do produto.
     * @return ProdutoDTO representando o produto atualizado.
     */
    @PutMapping
    public ResponseEntity<ProdutoDTO> atualizar(@Valid @RequestBody ProdutoModel produtoExistente) {
        ProdutoDTO produtoExistenteDTO = produtoService.atualizar(produtoExistente);
        return ResponseEntity.status(HttpStatus.OK).body(produtoExistenteDTO);
    }

    /**
     * Deleta um produto da base de dados.
     *
     * @param produtoModel ProdutoModel contendo os dados do garçom a ser deletado.
     * @return Mensagem indicando o sucesso da exclusão.
     */
    @DeleteMapping
    public ResponseEntity<String> deletar(@Valid @RequestBody ProdutoModel produtoModel) {
        produtoService.deletar(produtoModel);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Produto excluído com sucesso.");
    }
}
