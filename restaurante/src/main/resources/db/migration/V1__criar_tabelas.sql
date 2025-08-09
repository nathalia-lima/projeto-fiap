-- Cria o tipo ENUM para o perfil do usuário
CREATE TYPE perfil_usuario AS ENUM ('CLIENTE', 'RESTAURANTE');


-- Cria a tabela usuario
DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_alteracao TIMESTAMP,
    perfil_usuario VARCHAR NOT NULL
);

-- Cria a tabela endereco
DROP TABLE IF EXISTS endereco;
CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    cep VARCHAR(9) NOT NULL,
    rua VARCHAR(100) NOT NULL,
    numero VARCHAR(20),
    bairro VARCHAR(100),
    cidade VARCHAR(100) NOT NULL,
    estado CHAR(2) NOT NULL,
    usuario_id INTEGER NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);