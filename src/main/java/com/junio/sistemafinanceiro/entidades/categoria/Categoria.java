package com.junio.sistemafinanceiro.entidades.categoria;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.junio.sistemafinanceiro.service.exceptions.DadoInvalidoException;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Entity(name = "Categoria")
@Table(name = "Categorias")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @JsonIgnore
    private Boolean ativo;

    public Categoria(DadosCadastroCategoria dados) {
        this.ativo = true;
        this.nome = dados.nome();
    }

    public void atualizarCategoria(DadosAtualizarCategoria dados) {
        if (StringUtils.isNotBlank(dados.nome())) {
            this.setNome(dados.nome());
        } else if (dados.nome() != null){
            throw new DadoInvalidoException("Nome não pode ser vazio.");
        }
    }
}
