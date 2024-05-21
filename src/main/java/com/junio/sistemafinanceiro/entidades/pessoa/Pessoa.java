package com.junio.sistemafinanceiro.entidades.pessoa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.junio.sistemafinanceiro.entidades.pessoa.endereco.Endereco;
import com.junio.sistemafinanceiro.entidades.lancamento.Lancamento;
import com.junio.sistemafinanceiro.service.exceptions.DadoInvalidoException;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Pessoa")
@Table(name = "Pessoas")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @JsonIgnore
    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "pessoa")
    private List<Lancamento> lancamentos = new ArrayList<>();

    public Pessoa(DadosCadastroPessoa dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.endereco = new Endereco(dados.endereco());
    }

    public void AtualizarPessoa(DadosAtualizarPessoa dados) {
        // atualizando Nome
        if (StringUtils.isNotBlank(dados.nome())) {
            if (dados.nome().length() < 3 || dados.nome().length() > 100) {
                throw new DadoInvalidoException("Nome deve ter entre 3 e 100 caracteres");
            }
            this.setNome(dados.nome());
        } else if (dados.nome() != null) {
            throw new DadoInvalidoException("Nome não pode ser vazio");
        }

        if (dados.endereco() != null) {

            // atualizando Logradouro
            if (StringUtils.isNotBlank(dados.endereco().getLogradouro())) {
                endereco.setLogradouro(dados.endereco().getLogradouro());
            } else if (dados.endereco().getLogradouro() != null) {
                throw new DadoInvalidoException("Logradouro não pode ser vazio");
            }

            // atualizando Bairro
            if (StringUtils.isNotBlank(dados.endereco().getBairro())) {
                endereco.setBairro(dados.endereco().getBairro());
            } else if (dados.endereco().getBairro() != null) {
                throw new DadoInvalidoException("Bairro não pode ser vazio");
            }

            // atualizando CEP
            if (StringUtils.isNotBlank(dados.endereco().getCep())) {
                if (!dados.endereco().getCep().matches("^[0-9]{8}$")) {
                    throw new DadoInvalidoException("CEP inválido.");
                }
                endereco.setCep(dados.endereco().getCep());
            } else if (dados.endereco().getCep() != null) {
                throw new DadoInvalidoException("CEP não pode ser vazio");
            }

            // atualizando Número
            if (StringUtils.isNotBlank(dados.endereco().getNumero())) {
                endereco.setNumero(dados.endereco().getNumero());
            } else if (dados.endereco().getNumero() != null) {
                throw new DadoInvalidoException("Número não pode ser vazio");
            }

            // atualizando Complemento
            if (StringUtils.isNotBlank(dados.endereco().getComplemento())) {
                endereco.setComplemento(dados.endereco().getComplemento());
            } else if (dados.endereco().getComplemento() != null) {
                throw new DadoInvalidoException("Complemento não pode ser vazio");
            }

            // atualizando Cidade
            if (StringUtils.isNotBlank(dados.endereco().getCidade())) {
                endereco.setCidade(dados.endereco().getCidade());
            } else if (dados.endereco().getCidade() != null) {
                throw new DadoInvalidoException("Cidade não pode ser vazio");
            }

            // atualizando UF
            if (dados.endereco().getUf() != null) {
                endereco.setUf(dados.endereco().getUf());
            }

            this.setEndereco(endereco);
        }
    }
}
