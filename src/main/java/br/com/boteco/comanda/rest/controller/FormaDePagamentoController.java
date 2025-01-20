package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.FormaDePagamentoModel;
import br.com.boteco.comanda.rest.dto.FormaDePagamentoDTO;
import br.com.boteco.comanda.service.FormaDePagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável por gerenciar as operações relacionadas aos mesas.
 */
@RestController
@RequestMapping("/formaDePagamento")
public class FormaDePagamentoController {

    /**
     * Instância do serviço de mesas, responsável por encapsular a lógica de negócios
     * e intermediar as operações entre o controlador e o repositório.
     */
    @Autowired
    private FormaDePagamentoService formaDePagamentoService;

    /**
     * Obtém a lista de todos as mesas cadastrados.
     *
     * @return Lista de FormaDePagamentoDTO representando as mesas cadastrados.
     */
    @GetMapping()
    public ResponseEntity<List<FormaDePagamentoDTO>> obterTodos() {
        List<FormaDePagamentoDTO> formaDePagamentoDTOS = formaDePagamentoService.obterTodos();
        return ResponseEntity.ok(formaDePagamentoDTOS);
    }

    /**
     * Obtém uma forma de pagamento pelo ID.
     *
     * @param id ID do forma de pagamento.
     * @return FormaDePagamentoDTO representando a forma de pagamento encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FormaDePagamentoDTO> obterPorId(@PathVariable Long id) {
        FormaDePagamentoDTO formaDePagamentoDTO = formaDePagamentoService.obterPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(formaDePagamentoDTO);
    }

    /**
     * Salva uma nova forma de pagamento na base de dados.
     *
     * @param novaFormaDePagamento FormaDePagamentoModel contendo os dados da nova forma de pagamento.
     * @return FormaDePagamentoDTO representando a forma de pagamento salva.
     */
    @PostMapping
    public ResponseEntity<FormaDePagamentoDTO> salvar(@Valid @RequestBody FormaDePagamentoModel novaFormaDePagamento) {
        FormaDePagamentoDTO novaFormaDePagamentoDTO = formaDePagamentoService.salvar(novaFormaDePagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaFormaDePagamentoDTO);
    }

    /**
     * Atualiza os dados de um forma de pagamento existente.
     *
     * @param novaFormaDePagamentoExistente FormaDePagamentoModel contendo os dados atualizados da forma de pagamento.
     * @return FormaDePagamentoDTO representando o forma de pagamento atualizado.
     */
    @PutMapping
    public ResponseEntity<FormaDePagamentoDTO> atualizar(@Valid @RequestBody FormaDePagamentoModel novaFormaDePagamentoExistente) {
        FormaDePagamentoDTO formaDePagamentoExistenteDTO = formaDePagamentoService.atualizar(novaFormaDePagamentoExistente);
        return ResponseEntity.status(HttpStatus.OK).body(formaDePagamentoExistenteDTO);
    }

    /**
     * Deleta uma forma de pagamento da base de dados.
     *
     * @param formaDePagamentoModel FormaDePagamentoModel contendo os dados da forma de pagamento a ser deletado.
     * @return Mensagem indicando o sucesso da exclusão.
     */
    @DeleteMapping
    public ResponseEntity<String> deletar(@Valid @RequestBody FormaDePagamentoModel formaDePagamentoModel) {
        formaDePagamentoService.deletar(formaDePagamentoModel);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Forma de Pagamento excluída com sucesso.");
    }
}


