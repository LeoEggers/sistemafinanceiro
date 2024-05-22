package com.junio.sistemafinanceiro.service;

import com.junio.sistemafinanceiro.entidades.lancamento.DadosAtualizarLancamento;
import com.junio.sistemafinanceiro.entidades.lancamento.DadosCadastroLancamento;
import com.junio.sistemafinanceiro.entidades.lancamento.Lancamento;
import com.junio.sistemafinanceiro.repositories.CategoriaRepository;
import com.junio.sistemafinanceiro.repositories.LancamentoRepository;
import com.junio.sistemafinanceiro.repositories.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
        return lancamentoRepository.findByAtivoTrue();
    }

    public Lancamento findLancamentoById(Long id) {
        Optional<Lancamento> lancamento = lancamentoRepository.findAtivoById(id);
        return lancamento.orElseThrow();
    }

    // Update
    public Lancamento updateLancamento(Long id, DadosAtualizarLancamento dados) {
        Lancamento lancamento = findLancamentoById(id);

        if (dados.descricao() != null) {
            lancamento.setDescricao(dados.descricao());
        }
        if (dados.observacao() != null) {
            lancamento.setObservacao(dados.observacao());
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
        if (dados.dataConclusao() != null) {
            lancamento.setDataConclusao(dados.dataConclusao());
        }
        if (dados.categoria() != null) {
            lancamento.setCategoria(dados.categoria());
        }
        if (dados.transacaoConcluida() != null) {
            lancamento.setTransacaoConcluida(dados.transacaoConcluida());
            lancamento.setDataConclusao(dados.transacaoConcluida() ? Instant.now() : null);
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
