package br.com.boteco.comanda.model;

import br.com.boteco.comanda.rest.dto.GarcomDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "garcom")
public class GarcomModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGarcom")
    private Long idGarcom;

    @NotNull(message = "Não admite valor nulo.")
    @NotBlank(message = "Não admite ausência de valor.")
    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @NotNull(message = "Não admite valor nulo.")
    @Column(name = "dataNascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotNull(message = "Não admite valor nulo.")
    @NotBlank(message = "Não admite ausência de valor.")
    @Size(min = 11, max = 11, message = "O valor deverá ser de 11 caracteres.")
    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @NotNull(message = "Não admite valor nulo.")
    @NotBlank(message = "Não admite ausência de valor.")
    @Size(min = 11, max = 11, message = "O valor deverá ser de 11 caracteres.")
    @Column(name = "telefone",  length = 11, nullable = false, unique = true)
    private String telefone;

    @NotNull(message = "Não admite valor nulo.")
    @NotBlank(message = "Não admite ausência de valor.")
    @Email(message = "E-mail inválido.")
    @Column(name = "email",  length = 255, nullable = false, unique = true)
    private String email;

    @NotNull(message = "Não admite valor nulo.")
    @NotBlank(message = "Não admite ausência de valor.")
    @Size(min = 1, max = 1, message = "O valor deverá ser de 1 caracter.")
    @Column(name = "sexo", length = 1, nullable = false)
    private String sexo;

    public GarcomDTO toDTO(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, GarcomDTO.class);
    }
}
