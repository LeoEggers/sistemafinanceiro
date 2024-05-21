CREATE TABLE Lançamentos
(
    id                  BIGINT                      NOT NULL AUTO_INCREMENT,
    ativo               BOOLEAN                     NOT NULL DEFAULT TRUE,
    descricao           VARCHAR(255)                NOT NULL,
    observacao          TEXT,
    valor               DOUBLE                      NOT NULL,
    data_lancamento     TIMESTAMP                   NOT NULL,
    data_vencimento     TIMESTAMP                   NOT NULL,
    data_conclusao      TIMESTAMP,
    categoria_id        BIGINT,
    tipo_lancamento     ENUM ('RECEITA', 'DESPESA') NOT NULL,
    pessoa_id           BIGINT                      NOT NULL,
    transacao_concluida BOOLEAN                     NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    FOREIGN KEY (categoria_id) REFERENCES Categorias (id),
    FOREIGN KEY (pessoa_id) REFERENCES Pessoas (id)
);
