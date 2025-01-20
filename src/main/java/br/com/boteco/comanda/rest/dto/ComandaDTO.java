package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.ComandaModel;
import br.com.boteco.comanda.model.GarcomModel;
import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ComandaDTO {

    @Column(name = "dataHoraAbertura", nullable = false)
    private LocalDate dataHoraAbertura;

    @Column(name = "dataHoraFechamento", nullable = false)
    private LocalDate dataFechamento;

    @Column(name = "valorTotalComanda", nullable = false)
    private BigDecimal valorTotalComanda;

    @Column(name = "valorGorjeta", nullable = false)
    private BigDecimal valorGorjeta;

    @Column(name = "status", length = 255, nullable = false)
    private String nome;

    @Column(name = "idMesa", nullable = false)
    private Long idMesa;

    @Column(name = "idGarcom", nullable = false)
    private Long idGarcom;

    @Column(name = "idProduto", nullable = false)
    private Long idproduto;

    public ComandaModel toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ComandaModel.class);
    }
}
