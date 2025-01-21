package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.GarcomModel;
import br.com.boteco.comanda.rest.dto.GarcomDTO;
import br.com.boteco.comanda.service.GarcomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável por gerenciar as operações relacionadas aos garçons.
 */
@RestController
@RequestMapping("/garcom")
public class GarcomController {

    /**
     * Instância do serviço de garçons, responsável por encapsular a lógica de negócios
     * e intermediar as operações entre o controlador e o repositório.
     */
    @Autowired
    private GarcomService garcomService;

    /**
     * Obtém a lista de todos os garçons cadastrados.
     *
     * @return Lista de GarcomDTO representando os garçons cadastrados.
     */
    @GetMapping()
    public ResponseEntity<List<GarcomDTO>> obterTodos(){
        List<GarcomDTO> garcomDTOS = garcomService.obterTodos();
        return ResponseEntity.ok(garcomDTOS);
    }

    /**
    *  Get com a funcionalidade de encontrar determinado atributo
    *  Estes atributos são definidos pela annotation @ResquestParam
    *  Use: /find?nome='nome garcom'
    */
    @GetMapping(path = "/find")
    public ResponseEntity<List<GarcomModel>> findByNome(@RequestParam(required = false) String nome) {
        return ResponseEntity.ok(garcomService.findByNome(nome));

    }

    /**
     * Obtém um garçom pelo ID.
     *
     * @param id ID do garçom.
     * @return GarcomDTO representando o garçom encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GarcomDTO> obterPorId(@PathVariable Long id) {
        GarcomDTO garcomDTO = garcomService.obterPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(garcomDTO);
    }

    /**
     * Salva um novo garçom na base de dados.
     *
     * @param novoGarcom GarcomModel contendo os dados do novo garçom.
     * @return GarcomDTO representando o garçom salvo.
     */
    @PostMapping
    public ResponseEntity<GarcomDTO> salvar(@Valid @RequestBody GarcomModel novoGarcom){
        GarcomDTO novoGarcomDTO = garcomService.salvar(novoGarcom);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoGarcomDTO);
    }

    /**
     * Atualiza os dados de um garçom existente.
     *
     * @param garcomExistente GarcomModel contendo os dados atualizados do garçom.
     * @return GarcomDTO representando o garçom atualizado.
     */
    @PutMapping
    public ResponseEntity<GarcomDTO> atualizar(@Valid @RequestBody GarcomModel garcomExistente){
        GarcomDTO garcomExistenteDTO = garcomService.atualizar(garcomExistente);
        return  ResponseEntity.status(HttpStatus.OK).body(garcomExistenteDTO);
    }

    /**
     * Deleta um garçom da base de dados.
     *
     * @param garcomModel GarcomModel contendo os dados do garçom a ser deletado.
     * @return Mensagem indicando o sucesso da exclusão.
     */
    @DeleteMapping
    public ResponseEntity<String> deletar(@Valid @RequestBody GarcomModel garcomModel) {
            garcomService.deletar(garcomModel);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("Garçom excluído com sucesso.");
    }
}