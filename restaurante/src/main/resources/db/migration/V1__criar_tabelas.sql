-- Cria a tabela perfil_usuario
CREATE TABLE perfil_usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) UNIQUE NOT NULL
);

INSERT INTO perfil_usuario (nome) VALUES
('CLIENTE'),
('DONO_RESTAURANTE'),
('ADMINISTRADOR');

-- Cria a tabela endereco
CREATE TABLE IF NOT EXISTS endereco (
    id SERIAL PRIMARY KEY,
    cep VARCHAR(9),
    rua VARCHAR(100),
    numero VARCHAR(20),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado CHAR(2)
);

-- Cria a tabela usuario
CREATE TABLE IF NOT EXISTS usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(14) UNIQUE,
    email VARCHAR(100) UNIQUE,
    senha VARCHAR(255),
    data_alteracao TIMESTAMP,
    perfil_usuario_id INTEGER NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    endereco_id INTEGER,
    CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id) ON DELETE CASCADE,
    CONSTRAINT fk_perfil_usuario FOREIGN KEY (perfil_usuario_id) REFERENCES perfil_usuario(id)
);

-- Cria a tabela restaurante
CREATE TABLE IF NOT EXISTS restaurante (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipo_cozinha VARCHAR(30),
    horario_funcionamento VARCHAR(30),
    endereco_id BIGINT,
    dono_id INTEGER NOT NULL,
    CONSTRAINT fk_dono FOREIGN KEY (dono_id) REFERENCES usuario(id),
    CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id) ON DELETE CASCADE
);

-- Cria a tabela item_cardapio
CREATE TABLE IF NOT EXISTS item_cardapio (
    id SERIAL PRIMARY KEY,
    restaurante_id BIGINT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    disponivel BOOLEAN,
    foto VARCHAR(255),
    CONSTRAINT fk_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurante(id)
);