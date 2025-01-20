package br.com.boteco.comanda.model;

import br.com.boteco.comanda.rest.dto.ComandaDTO;
import br.com.boteco.comanda.rest.dto.ConsumoDTO;
import br.com.boteco.comanda.rest.dto.GarcomDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comanda")
public class ComandaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComanda")
    private Long idComanda;

    @NotNull(message = "Não admite valor nulo.")
    @Column(name = "dataHoraAbertura", nullable = false)
    private LocalDate dataHoraAbertura;

    @NotNull(message = "Não admite valor nulo.")
    @Column(name = "dataHoraFechamento", nullable = false)
    private LocalDate dataFechamento;

    @NotNull(message = "O valor não pode ser nulo")
    @NotBlank(message = "Não admite ausência de valor")
    @Column(name = "valorTotalComanda", nullable = false)
    private BigDecimal valorTotalComanda;

    @NotNull(message = "O valor não pode ser nulo")
    @NotBlank(message = "Não admite ausência de valor")
    @Column(name = "valorGorjeta", nullable = false)
    private BigDecimal valorGorjeta;

    @NotNull(message = "Não admite valor nulo.")
    @NotBlank(message = "Não admite ausência de valor.")
    @Column(name = "status", length = 255, nullable = false)
    private String nome;

    @NotNull(message = "Não admite valor nulo.")
    @NotBlank(message = "Não admite ausência de valor.")
    @Column(name = "idMesa", nullable = false)
    private Long idMesa;

    @NotNull(message = "Não admite valor nulo.")
    @NotBlank(message = "Não admite ausência de valor.")
    @Column(name = "idGarcom", nullable = false)
    private Long idGarcom;

    @NotNull(message = "Não admite valor nulo.")
    @NotBlank(message = "Não admite ausência de valor.")
    @Column(name = "idProduto", nullable = false)
    private Long idProduto;

    public ComandaDTO toDTO(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ComandaDTO.class);
    }
}
