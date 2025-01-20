package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.ComandaModel;
import br.com.boteco.comanda.rest.dto.ComandaDTO;
import br.com.boteco.comanda.service.ComandaService;
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
@RequestMapping("/comanda")
public class ComandaController {

    /**
     * Instância do serviço de mesas, responsável por encapsular a lógica de negócios
     * e intermediar as operações entre o controlador e o repositório.
     */
    @Autowired
    private ComandaService comandaService;

    /**
     * Obtém a lista de todos as mesas cadastrados.
     *
     * @return Lista de ComandaDTO representando as mesas cadastrados.
     */
    @GetMapping()
    public ResponseEntity<List<ComandaDTO>> obterTodos() {
        List<ComandaDTO> comandaDTOS = comandaService.obterTodos();
        return ResponseEntity.ok(comandaDTOS);
    }

    /**
     * Obtém uma comanda pelo ID.
     *
     * @param id ID do comanda.
     * @return ComandaDTO representando a comanda encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ComandaDTO> obterPorId(@PathVariable Long id) {
        ComandaDTO comandaDTO = comandaService.obterPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(comandaDTO);
    }

    /**
     * Salva uma nova comanda na base de dados.
     *
     * @param novaComanda MesaModel contendo os dados da nova comanda.
     * @return ComandaDTO representando a comanda salvo.
     */
    @PostMapping
    public ResponseEntity<ComandaDTO> salvar(@Valid @RequestBody ComandaModel novaComanda) {
        ComandaDTO novaComandaDTO = comandaService.salvar(novaComanda);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaComandaDTO);
    }

    /**
     * Atualiza os dados de uma comanda existente.
     *
     * @param comandaExistente MesaModel contendo os dados atualizados da comanda.
     * @return ComandaDTO representando a comanda atualizado.
     */
    @PutMapping
    public ResponseEntity<ComandaDTO> atualizar(@Valid @RequestBody ComandaModel comandaExistente) {
        ComandaDTO comandaExistenteDTO = comandaService.atualizar(comandaExistente);
        return ResponseEntity.status(HttpStatus.OK).body(comandaExistenteDTO);
    }

    /**
     * Deleta uma comanda da base de dados.
     *
     * @param comandaModel MesaModel contendo os dados da comanda a ser deletado.
     * @return Mensagem indicando o sucesso da exclusão.
     */
    @DeleteMapping
    public ResponseEntity<String> deletar(@Valid @RequestBody ComandaModel comandaModel) {
        comandaService.deletar(comandaModel);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Comanda excluído com sucesso.");
    }
}
