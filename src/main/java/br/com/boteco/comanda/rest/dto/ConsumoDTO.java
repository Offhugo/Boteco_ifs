package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.ConsumoModel;
import br.com.boteco.comanda.model.GarcomModel;
import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ConsumoDTO {

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "dataHoraConsumo", nullable = false)
    private LocalDateTime dataHoraConsumo;

    @Column(name = "precoUnitarioVendido", nullable = false)
    private BigDecimal prcounitarioVendido;

    @Column(name = "precoTotal")
    private BigDecimal precoTotal;

    @Column(name = "idComanda", nullable = false)
    private Long idComanda;

    @Column(name = "idProduto", nullable = false)
    private Long idProduto;

    public ConsumoModel toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ConsumoModel.class);
    }
}
