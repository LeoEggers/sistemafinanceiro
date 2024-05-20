package com.junio.sistemafinanceiro.entidades.pessoa;

import com.junio.sistemafinanceiro.entidades.pessoa.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosCadastroPessoa(
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        String nome,
        @NotNull @Valid
        DadosEndereco endereco
) {
}
