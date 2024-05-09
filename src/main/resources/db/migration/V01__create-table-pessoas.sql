CREATE TABLE Pessoas
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    nome        VARCHAR(100) NOT NULL,
    ativo       BOOLEAN      NOT NULL DEFAULT TRUE,
    logradouro  VARCHAR(100) NOT NULL,
    bairro      VARCHAR(100) NOT NULL,
    cep         VARCHAR(9)   NOT NULL,
    complemento VARCHAR(100),
    numero      VARCHAR(20),
    uf          CHAR(2)      NOT NULL,
    cidade      VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);