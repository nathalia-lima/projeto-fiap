O que é o sistema do projeto 

O sistema é uma aplicação backend desenvolvida em Java com Spring Boot, focada na gestão de restaurantes. Ele permite cadastrar e gerenciar usuários, perfis, restaurantes e cardápios, armazenando todos os dados em um banco PostgreSQL e garantindo segurança através de autenticação e validação das informações. 

 

Como ele funciona (Arquitetura) 

O projeto segue os princípios da arquitetura limpa (Clean Architecture), organizando o código em camadas bem definidas e separando responsabilidades de forma clara: 

Controller 

Responsável por receber e responder as requisições HTTP da API (ex: ItemCardapioController, UsuarioController). 

Mapeia os endpoints e valida os dados iniciais da requisição. 

Encaminha as operações para os casos de uso (usecases/services). 

DTOs 

Estruturas de dados utilizadas para transportar informações entre as camadas (ex: ItemCardapioDTO, UsuarioDTO). 

Facilitam a validação, documentação e padronização dos dados trocados via API. 

Domínio 

Contém as entidades centrais do negócio, representando os objetos e suas regras (ex: ItemCardapio, Usuario, Restaurante). 

Define métodos e comportamentos essenciais para cada entidade. 

Usecases/Services 

Implementam a lógica de negócio (ex: CadastrarItemCardapio, EditarItemCardapio, ExcluirItemCardapio). 

Orquestram as operações entre repositórios, domínio e apresentação. 

Realizam validações e regras específicas do sistema. 

Mapper 

Convertem dados entre entidades de banco, objetos de domínio e DTOs (ex: RespostaMapper). 

Garantem que cada camada trabalhe com o formato de dado mais adequado. 

Entidades de Persistência 

Representam as tabelas do banco de dados (ex: ItemCardapioEntity, UsuarioEntity, RestauranteEntity, PerfilUsuarioEntity). 

Utilizam anotações do JPA/Hibernate para mapear os campos e relacionamentos. 

Repositórios 

Interface com o banco de dados, usando Spring Data JPA (ex: ItemCardapioRepository, UsuarioRepository, RestauranteRepository). 

Oferecem métodos para salvar, buscar, editar e excluir entidades. 

Segurança 

Configura autenticação, autorização e criptografia de senhas (ex: ConfiguracoesSeguranca). 

Garante que apenas usuários autenticados e autorizados possam acessar recursos sensíveis da API. 

Instruções 

Abra um terminal na raiz do projeto (onde o arquivo docker-compose.yml está localizado). 

Execute o seguinte comando para construir a imagem do projeto e subir os contêineres: 

docker-compose up --build A aplicação estará disponível em http://localhost:8000. 

O banco de dados PostgreSQL estará disponível na porta 5432. 

O docker-compose.yml configura as seguintes variáveis de ambiente para a aplicação: 

DATABASE_URL: jdbc:postgresql://db:5432/restaurante 

DATABASE_USERNAME: postgres 

DATABASE_PASSWORD: 192277 
