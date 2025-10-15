DROP TABLE IF EXISTS item_cardapio CASCADE;
DROP TABLE IF EXISTS restaurante CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS endereco CASCADE;
DROP TABLE IF EXISTS perfil_usuario CASCADE;

CREATE TABLE IF NOT EXISTS perfil_usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS endereco (
    id SERIAL PRIMARY KEY,
    cep VARCHAR(20),
    rua VARCHAR(255),
    numero VARCHAR(50),
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(30),
    email VARCHAR(255),
    senha VARCHAR(255),
    data_alteracao TIMESTAMP,
    perfil_usuario_id INTEGER,
    ativo BOOLEAN,
    endereco_id INTEGER,
    CONSTRAINT fk_usuario_perfil FOREIGN KEY (perfil_usuario_id) REFERENCES perfil_usuario(id),
    CONSTRAINT fk_usuario_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

CREATE TABLE IF NOT EXISTS restaurante (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipo_cozinha VARCHAR(30),
    horario_funcionamento VARCHAR(30),
    endereco_id INTEGER,
    dono_id INTEGER NOT NULL,
    CONSTRAINT fk_restaurante_dono FOREIGN KEY (dono_id) REFERENCES usuario(id),
    CONSTRAINT fk_restaurante_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS item_cardapio (
    id SERIAL PRIMARY KEY,
    restaurante_id INTEGER NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2),
    disponivel BOOLEAN,
    foto VARCHAR(255),
    CONSTRAINT fk_item_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurante(id)
);

INSERT INTO perfil_usuario (nome) VALUES ('CLIENTE');
INSERT INTO perfil_usuario (nome) VALUES ('DONO_RESTAURANTE');
INSERT INTO perfil_usuario (nome) VALUES('ADMINISTRADOR');

INSERT INTO endereco (cep, rua, numero, complemento, bairro, cidade, estado) VALUES
('01001-000', 'Av. Paulista', '1000', 'Apto 101', 'Bela Vista', 'São Paulo', 'SP');
INSERT INTO endereco (cep, rua, numero, complemento, bairro, cidade, estado) VALUES
('20010-000', 'Rua das Flores', '200', NULL, 'Centro', 'Rio de Janeiro', 'RJ');
INSERT INTO endereco (cep, rua, numero, complemento, bairro, cidade, estado) VALUES
('30120-000', 'Rua do Comércio', '50', 'Sala 2', 'Savassi', 'Belo Horizonte', 'MG');


INSERT INTO usuario (nome, cpf, email, senha, data_alteracao, perfil_usuario_id, ativo, endereco_id) VALUES
('João Silva', '111.111.111-11', 'joao@example.com', 'senha1', now(), 1, TRUE, 1);
INSERT INTO usuario (nome, cpf, email, senha, data_alteracao, perfil_usuario_id, ativo, endereco_id) VALUES
('Maria Pereira', '222.222.222-22', 'maria@example.com', 'senha2', now(), 2, TRUE, 2);
INSERT INTO usuario (nome, cpf, email, senha, data_alteracao, perfil_usuario_id, ativo, endereco_id) VALUES
('Carlos Souza', '333.333.333-33', 'carlos@example.com', 'senha3', now(), 3, TRUE, 3);


INSERT INTO restaurante (nome, tipo_cozinha, horario_funcionamento, endereco_id, dono_id) VALUES
('Restaurante Bom Sabor', 'BRASILEIRA', '09:00-22:00', 1, 2);
INSERT INTO restaurante (nome, tipo_cozinha, horario_funcionamento, endereco_id, dono_id) VALUES
('La Pasta', 'ITALIANA', '11:00-23:00', 2, 2);
INSERT INTO restaurante (nome, tipo_cozinha, horario_funcionamento, endereco_id, dono_id) VALUES
('Sushi House', 'JAPONESA', '12:00-22:30', 3, 1);


INSERT INTO item_cardapio (restaurante_id, nome, descricao, preco, disponivel, foto) VALUES
(1, 'Feijoada Completa', 'Feijoada tradicional com couve e laranja', 39.90, TRUE, 'feijoada.jpg');
INSERT INTO item_cardapio (restaurante_id, nome, descricao, preco, disponivel, foto) VALUES
(2, 'Spaghetti Carbonara', 'Massa com molho cremoso e pancetta', 29.50, TRUE, 'carbonara.jpg');
INSERT INTO item_cardapio (restaurante_id, nome, descricao, preco, disponivel, foto) VALUES
(3, 'Salmão Grelhado', 'Salmão grelhado com legumes', 45.00, FALSE, 'salmao.jpg');