package br.com.boteco.comanda.model;

import br.com.boteco.comanda.rest.dto.ProdutoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Produto")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduto")
    private Long idProduto;

    @NotNull(message = "Não admite valor nulo.")
    @NotBlank(message = "Não admite ausência de valor.")
    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @NotNull(message = "Não admite valor nulo.")
    @NotBlank(message = "Não admite ausência de valor.")
    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    @NotNull(message = "Não admite valor nulo.")
    @Min(value = 0, message = "O valor não pode ser menor que zero")
    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @NotNull(message = "Não admite valor nulo.")
    @Column(name = "status", length = 255, nullable = false)
    private String status;

    public ProdutoDTO toDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ProdutoDTO.class);
    }
}
