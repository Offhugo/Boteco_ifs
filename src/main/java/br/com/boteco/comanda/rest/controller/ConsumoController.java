package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.ConsumoModel;
import br.com.boteco.comanda.rest.dto.ConsumoDTO;
import br.com.boteco.comanda.service.ConsumoService;
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
@RequestMapping("/consumo")
public class ConsumoController {

    /**
     * Instância do serviço de mesas, responsável por encapsular a lógica de negócios
     * e intermediar as operações entre o controlador e o repositório.
     */
    @Autowired
    private ConsumoService consumoService;

    /**
     * Obtém a lista de todos as mesas cadastrados.
     *
     * @return Lista de ConsumoDTO representando as mesas cadastrados.
     */
    @GetMapping()
    public ResponseEntity<List<ConsumoDTO>> obterTodos() {
        List<ConsumoDTO> consumoDTOS = consumoService.obterTodos();
        return ResponseEntity.ok(consumoDTOS);
    }

    /**
     * Obtém um consumo pelo ID.
     *
     * @param id ID do consumo.
     * @return ConsumoDTO representando o consumo encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ConsumoDTO> obterPorId(@PathVariable Long id) {
        ConsumoDTO consumoDTO = consumoService.obterPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(consumoDTO);
    }

    /**
     * Salva um novo consumo na base de dados.
     *
     * @param novoConsumo MesaModel contendo os dados do novo consumo.
     * @return ConsumoDTO representando o consumo salvo.
     */
    @PostMapping
    public ResponseEntity<ConsumoDTO> salvar(@Valid @RequestBody ConsumoModel novoConsumo) {
        ConsumoDTO novoConsumoDTO = consumoService.salvar(novoConsumo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoConsumoDTO);
    }

    /**
     * Atualiza os dados de um consumo existente.
     *
     * @param novoConsumoExistente MesaModel contendo os dados atualizados do consumo.
     * @return ConsumoDTO representando o consumo atualizado.
     */
    @PutMapping
    public ResponseEntity<ConsumoDTO> atualizar(@Valid @RequestBody ConsumoModel novoConsumoExistente) {
        ConsumoDTO consumoExistenteDTO = consumoService.atualizar(novoConsumoExistente);
        return ResponseEntity.status(HttpStatus.OK).body(consumoExistenteDTO);
    }

    /**
     * Deleta um consumo da base de dados.
     *
     * @param formaDePagamentoModel MesaModel contendo os dados do consumo a ser deletado.
     * @return Mensagem indicando o sucesso da exclusão.
     */
    @DeleteMapping
    public ResponseEntity<String> deletar(@Valid @RequestBody ConsumoModel formaDePagamentoModel) {
        consumoService.deletar(formaDePagamentoModel);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Consumo excluído com sucesso.");
    }
}
