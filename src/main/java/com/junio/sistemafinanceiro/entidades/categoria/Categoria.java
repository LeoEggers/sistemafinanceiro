package com.junio.sistemafinanceiro.entidades.categoria;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
}
