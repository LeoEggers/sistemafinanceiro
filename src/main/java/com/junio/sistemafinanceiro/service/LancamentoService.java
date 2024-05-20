package com.junio.sistemafinanceiro.service;

import com.junio.sistemafinanceiro.entidades.lancamento.DadosAtualizarLancamento;
import com.junio.sistemafinanceiro.entidades.lancamento.DadosCadastroLancamento;
import com.junio.sistemafinanceiro.entidades.lancamento.Lancamento;
import com.junio.sistemafinanceiro.repositories.CategoriaRepository;
import com.junio.sistemafinanceiro.repositories.LancamentoRepository;
import com.junio.sistemafinanceiro.repositories.PessoaRepository;
import com.junio.sistemafinanceiro.service.exceptions.DadoInvalidoException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final PessoaRepository pessoaRepository;
    private final CategoriaRepository categoriaRepository;

    // Create
    public Lancamento createLancamento(DadosCadastroLancamento dados) {

        var lancamento = new Lancamento(dados);

        // Atribuindo a Pessoa
        PessoaService ps = new PessoaService(pessoaRepository);
        lancamento.setPessoa(ps.findPessoaById(dados.idPessoa()));

        // Atribuindo a Categoria
        CategoriaService cs = new CategoriaService(categoriaRepository);
        lancamento.setCategoria(cs.findCategoriaById(dados.idCategoria()));

        return lancamentoRepository.save(lancamento);
    }

    // Read
    public List<Lancamento> findAllLancamentos() {
        return lancamentoRepository.findAllByAtivoIsTrue();
    }

    public Lancamento findLancamentoById(Long id) {
        Optional<Lancamento> lancamento = lancamentoRepository.findByIdAndAtivoIsTrue(id);
        return lancamento.orElseThrow();
    }

    // Update
    public Lancamento updateLancamento(Long id, DadosAtualizarLancamento dados) {
        Lancamento lancamento = findLancamentoById(id);

        if (StringUtils.isNotBlank(dados.descricao())) {
            lancamento.setDescricao(dados.descricao());
        } else if (dados.descricao() != null) {
            throw new DadoInvalidoException("Descrição não pode ser vazia");
        }

        if (StringUtils.isNotBlank(dados.observacao())) {
            lancamento.setObservacao(dados.observacao());
        } else if (dados.observacao() != null) {
            throw new DadoInvalidoException("Observação não pode ser vazia");
        }

        if (dados.valor() != null) {
            lancamento.setValor(dados.valor());
        }

        if (dados.dataLancamento() != null) {
            lancamento.setDataLancamento(dados.dataLancamento());
        }

        if (dados.dataVencimento() != null) {
            lancamento.setDataVencimento(dados.dataVencimento());
        }

        if (dados.categoria() != null) {
            lancamento.setCategoria(dados.categoria());
        }

        return lancamentoRepository.save(lancamento);
    }

    // Delete
    public void deleteLogicoLancamento(Long id) {
        Lancamento lancamento = findLancamentoById(id);
        lancamento.setAtivo(false);
        lancamentoRepository.save(lancamento);
    }
}
