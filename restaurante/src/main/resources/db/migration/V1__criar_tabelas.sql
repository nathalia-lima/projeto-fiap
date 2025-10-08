-- Cria o tipo ENUM para o perfil do usuário
CREATE TYPE perfil_usuario AS ENUM ('CLIENTE', 'RESTAURANTE');

-- Cria a tabela usuario
DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(14) UNIQUE,
    email VARCHAR(100) UNIQUE,
    senha VARCHAR(255),
    data_alteracao TIMESTAMP,
    perfil_usuario perfil_usuario,
    ativo BOOLEAN DEFAULT TRUE
);

-- Cria a tabela endereco
DROP TABLE IF EXISTS endereco;
CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    cep VARCHAR(9),
    rua VARCHAR(100),
    numero VARCHAR(20),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado CHAR(2),
    usuario_id INTEGER NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

-- Cria a tabela restaurante
DROP TABLE IF EXISTS restaurante;
CREATE TABLE restaurante (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) UNIQUE NOT NULL,
    telefone VARCHAR(15),
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    endereco_id BIGINT,
    CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

-- Cria a tabela item_cardapio
DROP TABLE IF EXISTS item_cardapio;
CREATE TABLE item_cardapio (
    id SERIAL PRIMARY KEY,
    restaurante_id BIGINT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    disponivel BOOLEAN,
    foto VARCHAR(255),
    CONSTRAINT fk_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurante(id)
);