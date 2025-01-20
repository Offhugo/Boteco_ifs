package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.MesaModel;
import br.com.boteco.comanda.rest.dto.MesaDTO;
import br.com.boteco.comanda.service.MesaService;
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
@RequestMapping("/mesa")
public class MesaController {

    /**
     * Instância do serviço de mesas, responsável por encapsular a lógica de negócios
     * e intermediar as operações entre o controlador e o repositório.
     */
    @Autowired
    private MesaService mesaService;

    /**
     * Obtém a lista de todos as mesas cadastrados.
     *
     * @return Lista de MesaDTO representando as mesas cadastrados.
     */
    @GetMapping()
    public ResponseEntity<List<MesaDTO>> obterTodos() {
        List<MesaDTO> mesaDTOS = mesaService.obterTodos();
        return ResponseEntity.ok(mesaDTOS);
    }

    /**
     * Obtém uma mesa pelo ID.
     *
     * @param id ID da mesa.
     * @return MesaDTO representando a mesa encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MesaDTO> obterPorId(@PathVariable Long id) {
        MesaDTO mesaDTO = mesaService.obterPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(mesaDTO);
    }

    /**
     * Salva uma nova mesa na base de dados.
     *
     * @param novaMesa MesaModel contendo os dados da nova mesa.
     * @return MesaDTO representando o produto salvo.
     */
    @PostMapping
    public ResponseEntity<MesaDTO> salvar(@Valid @RequestBody MesaModel novaMesa) {
        MesaDTO novaMesaDTO = mesaService.salvar(novaMesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMesaDTO);
    }

    /**
     * Atualiza os dados de uma mesa existente.
     *
     * @param mesaExistente MesaModel contendo os dados atualizados da mesa.
     * @return MesaDTO representando a mesa atualizado.
     */
    @PutMapping
    public ResponseEntity<MesaDTO> atualizar(@Valid @RequestBody MesaModel mesaExistente) {
        MesaDTO mesaExistenteDTO = mesaService.atualizar(mesaExistente);
        return ResponseEntity.status(HttpStatus.OK).body(mesaExistenteDTO);
    }

    /**
     * Deleta uma mesa da base de dados.
     *
     * @param mesaModel MesaModel contendo os dados da mesa a ser deletada.
     * @return Mensagem indicando o sucesso da exclusão.
     */
    @DeleteMapping
    public ResponseEntity<String> deletar(@Valid @RequestBody MesaModel mesaModel) {
        mesaService.deletar(mesaModel);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Mesa excluída com sucesso.");
    }
}

