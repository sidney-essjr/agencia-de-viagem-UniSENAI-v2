# Desafio 2 - API para Agência de Viagem

## Características

Tecnologias

- Java
- Spring Boot

Objetivo

- API para permitir que outras empresas de turismo e aplicativos de terceiros integrem-se ao seu sistema.

Escopo

- Informações sobre destinos, pacotes de viagens, disponibilidade de hotéis e atividades turísticas. Implementando segurança e conexão com a base de dados

Endpoints

- (POST) Cadastrar os destinos de viagem;
- (GET) Listar todos os destinos de viagem disponíveis;
- (GET) Pesquisar destinos por nome ou localização;
- (GET) Visualizar informações detalhadas sobre um destino específico;
- (PATCH) Avaliação de Destino de Viagens
- (POST) Reservar pacotes de viagens para um destino;
- (DELETE) Excluir destino.

---

### Funcionalidades

- [x]  Endpoint de Cadastro de Destinos de Viagem:
    
    Endpoint que permita inserir um novo destino de viagem.
    
- [x]  Endpoint de Listagem de Destinos de Viagem:
    
    Endpoint que retorne a lista de todos os destinos disponíveis.
    

- [x]  Endpoint de Pesquisa de Destinos:
    
    Endpoint para pesquisar destinos por nome ou localização e retornar os resultados.
    
- [x]  Endpoint de Visualização de Informações Detalhadas:
    
    Endpoint que permita visualizar informações detalhadas sobre um destino específico.
    
- [x]  Endpoint de Avaliação de Destino de Viagens:
    
    Endpoint que permita receber uma nota de avaliação de 1 a 10, com o objetivo de alterar a média de determinado destino. O sistema deve receber a avaliação e calcular a nova nota com base na nota já existente.
    
- [x]  Endpoint de Exclusão de Destinos de Viagem:
    
    Endpoint que permita excluir um determinado destino de viagem.
    
- [x]  Endpoint para registro de um novo usuário
    
    Endpoint permite a criação de um usuário utilizando username e senha
    
- [x]  Endpoint para login de um usuário
    
    Endpoint permite que um usuário com credenciais válidas acesse rotas autenticadas
    

---

## Database config

### Container PostgreSQL

```bash
docker-compose up -d
```

### Interface gráfica pgAdmin

```json
URL: [http://localhost:5050](http://localhost:5050)
E-mail: admin@pgadmin.com
Senha: admin

Host: postgres
Porta: 5432
Usuário: admin
Senha: secret
```

---

## Documentação - Swagger - OpenAPI

```json
URL: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
```

---

### Classes do Projeto

### **1. Classe `Destino`**

**Descrição**: Representa os destinos de viagem cadastrados no sistema.

**Campos**:

- `id` (UUID): Identificador único do destino.
- `nome` (String): Nome do destino.
- `localizacao` (String): Localização geográfica (cidade, estado, país).
- `descricao` (String): Descrição detalhada do destino.
- `mediaAvaliacao` (Float): Média de avaliações do destino.
- `numeroAvaliacoes` (Integer): Contador de avaliações recebidas.
- `dataCadastro` (Date): Data de criação do registro.

**Métodos**:

- `calcularMediaAvaliacao(novaNota: Float): void`: Atualiza a média de avaliação com base na nova nota recebida.

---

### **2. Classe `PacoteDeViagem`**

**Descrição**: Representa os pacotes de viagem relacionados a um destino.

**Campos**:

- `id` (UUID): Identificador único do pacote.
- `idDestino` (UUID): Referência ao destino relacionado.
- `nome` (String): Nome do pacote.
- `descricao` (String): Descrição detalhada do pacote.
- `preco` (Float): Preço do pacote.
- `disponibilidade` (Boolean): Indica se o pacote está disponível.

---

### **3. Classe `Reserva`**

**Descrição**: Representa as reservas realizadas para os pacotes de viagem.

**Campos**:

- `id` (UUID): Identificador único da reserva.
- `idPacote` (UUID): Referência ao pacote reservado.
- `dataReserva` (Date): Data da reserva.
- `quantidadePessoas` (Integer): Número de pessoas na reserva.

---

### **4. Classe `Avaliacao`**

**Descrição**: Representa as avaliações feitas em um destino.

**Campos**:

- `id` (UUID): Identificador único da avaliação.
- `idDestino` (UUID): Referência ao destino avaliado.
- `nota` (Integer): Nota de avaliação (1 a 10).
- `comentario` (String): Comentário opcional do avaliador.
- `dataAvaliacao` (Date): Data da avaliação.

---

### **5. Classe `User`**

**Descrição**: Representa as avaliações feitas em um destino.

**Campos**:

- `id` (UUID): Identificador único da avaliação.
- `username` (UUID): Nome de usuário para acesso ao sistema.
- `password` (String): Password para acesso ao sistema.
