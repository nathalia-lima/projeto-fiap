-- Cria o tipo ENUM para o perfil do usuário
CREATE TYPE perfil_usuario AS ENUM ('CLIENTE', 'RESTAURANTE');


-- Cria a tabela usuario
DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) ,
    cpf VARCHAR(14) UNIQUE,
    email VARCHAR(100) UNIQUE,
    senha VARCHAR(255) ,
    data_alteracao TIMESTAMP,
    perfil_usuario VARCHAR ,
    ativo BOOLEAN DEFAULT TRUE
);

-- Cria a tabela endereco
DROP TABLE IF EXISTS endereco;
CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    cep VARCHAR(9) ,
    rua VARCHAR(100) ,
    numero VARCHAR(20),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100) ,
    estado CHAR(2) ,
    usuario_id INTEGER NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);