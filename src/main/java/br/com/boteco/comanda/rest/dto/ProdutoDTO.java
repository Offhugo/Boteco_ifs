package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.ProdutoModel;
import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ProdutoDTO {

    @Column(name = "idProduto", nullable = false)
    private Long idProduto;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    @Column(name = "preco", nullable = false)
    private float preco;

    @Column(name = "status", length =  255,nullable = false)
    private String status;

    public ProdutoModel toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ProdutoModel.class);
    }
}
