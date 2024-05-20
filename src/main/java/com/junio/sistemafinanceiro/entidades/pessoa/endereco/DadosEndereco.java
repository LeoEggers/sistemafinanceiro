package com.junio.sistemafinanceiro.entidades.pessoa.endereco;

import com.junio.sistemafinanceiro.entidades.pessoa.endereco.enums.UnidadeFederativa;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DadosEndereco(
        @Size(min = 3, max = 100, message = "Logradouro deve ter entre 3 e 100 caracteres")
        String logradouro,

        @Size(min = 1, max = 100, message = "Bairro deve ter entre 1 e 100 caracteres")
        String bairro,

        @Pattern(regexp = "^[0-9]{5}-?[0-9]{3}$", message = "CEP deve estar no formato 12345-678 ou 12345678")
        String cep,

        @Size(min = 1, max = 100, message = "Cidade deve ter entre 1 e 100 caracteres")
        String cidade,

        @NotNull(message = "UF não pode estar em branco")
        UnidadeFederativa uf,

        @Size(max = 100, message = "Complemento deve ter no máximo 100 caracteres")
        String complemento,

        @Size(max = 20, message = "Número deve ter no máximo 20 caracteres")
        String numero
) {}
