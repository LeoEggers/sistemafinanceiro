package com.junio.sistemafinanceiro.entidades.lancamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.junio.sistemafinanceiro.entidades.categoria.Categoria;
import com.junio.sistemafinanceiro.entidades.lancamento.enums.TipoLancamento;
import com.junio.sistemafinanceiro.entidades.pessoa.Pessoa;
import com.junio.sistemafinanceiro.service.exceptions.DadoInvalidoException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.ZoneId;

@Entity(name = "Lançamento")
@Table(name = "Lançamentos")
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
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
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
            timezone = "GMT")
    private Instant dataConclusao;
    @ManyToOne
    private Categoria categoria = new Categoria();
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento;
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
    @JsonIgnore
    private Boolean ativo;
    private Boolean transacaoConcluida = false;

    public Lancamento(DadosCadastroLancamento dados) {
        this.ativo = true;
        this.descricao = dados.descricao();
        this.observacao = dados.observacao();
        this.valor = dados.valor();
        this.tipoLancamento = dados.tipoLancamento();

        this.dataLancamento = Instant.now();
        this.dataVencimento = this.dataLancamento.atZone(ZoneId.systemDefault()).plusDays(dados.diasParaOVencimento()).toInstant();
        this.dataConclusao = dados.transacaoConcluida() ? Instant.now() : null;
    }

    public void atualizarLancamento(DadosAtualizarLancamento dados){
        if (StringUtils.isNotBlank(dados.descricao())) {
            this.setDescricao(dados.descricao());
        } else if (dados.descricao() != null) {
            throw new DadoInvalidoException("Descrição não pode ser vazia");
        }

        if (StringUtils.isNotBlank(dados.observacao())) {
            this.setObservacao(dados.observacao());
        } else if (dados.observacao() != null) {
            throw new DadoInvalidoException("Observação não pode ser vazia");
        }

        if (dados.valor() != null) {
            this.setValor(dados.valor());
        }

        if (dados.dataLancamento() != null) {
            this.setDataLancamento(dados.dataLancamento());
        }

        if (dados.dataVencimento() != null) {
            this.setDataVencimento(dados.dataVencimento());
        }

        if (dados.dataConclusao() != null) {
            this.setDataConclusao(dados.dataConclusao());
        }

        if (dados.categoria() != null) {
            this.setCategoria(dados.categoria());
        }

        if (dados.transacaoConcluida() != null){
            this.setTransacaoConcluida(dados.transacaoConcluida());
            this.dataConclusao = this.transacaoConcluida ? Instant.now() : null;
        }
    }
}
