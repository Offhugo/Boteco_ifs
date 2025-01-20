package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.FormaDePagamentoModel;
import br.com.boteco.comanda.model.GarcomModel;
import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class FormaDePagamentoDTO {

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    public FormaDePagamentoModel toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, FormaDePagamentoModel.class);
    }
}
