package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.MesaModel;
import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class MesaDTO {

    @Column(name = "numero", length = 100, nullable = false)
    private int numero;

    @Column(name = "status", length =  255,nullable = false)
    private String status;

    public MesaModel toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, MesaModel.class);
    }
}
