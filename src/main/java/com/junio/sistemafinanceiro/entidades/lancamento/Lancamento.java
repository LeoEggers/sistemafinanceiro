package com.junio.sistemafinanceiro.entidades.lancamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.junio.sistemafinanceiro.entidades.categoria.Categoria;
import com.junio.sistemafinanceiro.enums.TipoLancamento;
import com.junio.sistemafinanceiro.entidades.pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.ZoneId;

@Entity(name = "Lançamento")
@Table(name = "Lançamentos")
@NoArgsConstructor
@Getter @Setter
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String observacao;
    private Double valor;
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
            timezone = "GMT")
    private Instant dataLancamento;
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
            timezone = "GMT")
    private Instant dataVencimento;
    @ManyToOne
    private Categoria categoria = new Categoria();
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento;
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
    @JsonIgnore
    private Boolean ativo;

    public Lancamento(DadosCadastroLancamento dados) {
        this.ativo = true;
        this.descricao = dados.descricao();
        this.observacao = dados.observacao();
        this.valor = dados.valor();
        this.tipoLancamento = dados.tipoLancamento();

        this.dataLancamento = Instant.now();
        this.dataVencimento = this.dataLancamento.atZone(ZoneId.systemDefault()).plusDays(30).toInstant();
    }
}
