CREATE TABLE Categorias
(
    id    BIGINT       NOT NULL AUTO_INCREMENT,
    ativo BOOLEAN      NOT NULL DEFAULT TRUE,
    nome  VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (nome)
);
