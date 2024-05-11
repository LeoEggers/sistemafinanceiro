package com.junio.sistemafinanceiro.entidades.pessoa;

import com.junio.sistemafinanceiro.entidades.endereco.DadosEndereco;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosCadastroPessoa(
        @NotEmpty
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        String nome,
        @NotNull
        DadosEndereco endereco
) {
}
