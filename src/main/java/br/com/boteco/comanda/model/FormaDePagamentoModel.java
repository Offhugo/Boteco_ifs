package br.com.boteco.comanda.model;

import br.com.boteco.comanda.rest.dto.FormaDePagamentoDTO;
import br.com.boteco.comanda.rest.dto.GarcomDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "formaDePagamento")
public class FormaDePagamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFormaDePagamento")
    private Long idFormaDePagamento;

    @NotNull(message = "Não admite valor nulo.")
    @NotBlank(message = "Não admite ausência de valor.")
    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @NotNull(message = "Não admite valor nulo.")
    @NotBlank(message = "Não admite ausência de valor.")
    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    public FormaDePagamentoDTO toDTO(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, FormaDePagamentoDTO.class);
    }

}
