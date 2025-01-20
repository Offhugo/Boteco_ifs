package br.com.boteco.comanda.model;

import br.com.boteco.comanda.rest.dto.ConsumoDTO;
import br.com.boteco.comanda.rest.dto.GarcomDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "consumo")
public class ConsumoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumo")
    private Long idConsumo;

    @NotNull(message = "Não admite valor nulo.")
    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @NotNull(message = "Não admite valor nulo.")
    @Column(name = "dataHoraConsumo", nullable = false)
    private LocalDateTime dataHoraConsumo;

    @NotNull(message = "Não admite valor nulo.")
    @Min(value = 0, message = "O valor não pode ser menor que zero")
    @Column(name = "precoUnitarioVendido", nullable = false)
    private BigDecimal prcounitarioVendido;

    @NotNull(message = "Não admit valor nulo.")
    @Min(value = 0, message = "O valor não pode ser menor que zero")
    @Column(name = "precoTotal")
    private BigDecimal precoTotal;

    @NotNull(message = "Não admite valor nulo.")
    @Column(name = "idComanda", nullable = false)
    private Long idComanda;

    @NotNull(message = "Não admite valor nulo.")
    @Column(name = "idProduto", nullable = false)
    private Long idProduto;

    public ConsumoDTO toDTO(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ConsumoDTO.class);
    }
}
