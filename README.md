# Desafio 2 - API para Agência de Viagem

## Características

Tecnologias

- Java
- Spring Boot

Objetivo

- API para permitir que outras empresas de turismo e aplicativos de terceiros integrem-se ao seu sistema.

Escopo

- Informações sobre destinos, pacotes de viagens, disponibilidade de hotéis e atividades turísticas.

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

- [ ]  Endpoint de Cadastro de Destinos de Viagem:
    
    Endpoint que permita inserir um novo destino de viagem.
    
- [ ]  Endpoint de Listagem de Destinos de Viagem:
    
    Endpoint que retorne a lista de todos os destinos disponíveis.
    

- [ ]  Endpoint de Pesquisa de Destinos:
    
    Endpoint para pesquisar destinos por nome ou localização e retornar os resultados.
    
- [ ]  Endpoint de Visualização de Informações Detalhadas:
    
    Endpoint que permita visualizar informações detalhadas sobre um destino específico.
    
- [ ]  Endpoint de Avaliação de Destino de Viagens:
    
    Endpoint que permita receber uma nota de avaliação de 1 a 10, com o objetivo de alterar a média de determinado destino. O sistema deve receber a avaliação e calcular a nova nota com base na nota já existente.
    
- [ ]  Endpoint de Exclusão de Destinos de Viagem:
    
    Endpoint que permita excluir um determinado destino de viagem.
    

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
- `idUsuario` (UUID): Identificador do cliente que fez a reserva.
- `dataReserva` (Date): Data da reserva.
- `quantidadePessoas` (Integer): Número de pessoas na reserva.

---

### **4. Classe `AtividadeTuristica`**

**Descrição**: Representa atividades turísticas associadas aos destinos.

**Campos**:

- `id` (UUID): Identificador único da atividade.
- `idDestino` (UUID): Referência ao destino relacionado.
- `nome` (String): Nome da atividade.
- `descricao` (String): Detalhes sobre a atividade.
- `preco` (Float): Custo da atividade (se aplicável).

---

### **5. Classe `Usuario`**

**Descrição**: Representa os usuários que interagem com a API, como turistas ou parceiros comerciais.

**Campos**:

- `id` (UUID): Identificador único do usuário.
- `nome` (String): Nome completo.
- `email` (String): E-mail do usuário.
- `senha` (String): Senha para autenticação (hash).
- `dataCadastro` (Date): Data de registro.

---

### **6. Classe `Avaliacao`**

**Descrição**: Representa as avaliações feitas em um destino.

**Campos**:

- `id` (UUID): Identificador único da avaliação.
- `idDestino` (UUID): Referência ao destino avaliado.
- `nota` (Integer): Nota de avaliação (1 a 10).
- `comentario` (String): Comentário opcional do avaliador.
- `dataAvaliacao` (Date): Data da avaliação.

---

### **Estrutura dos Endpoints**

### Cadastrar Destinos

- **Rota**: `POST /destinos`
- **Entrada**: Dados da classe `Destino`.
- **Saída**: Detalhes do destino criado.

### Listar Destinos

- **Rota**: `GET /destinos`
- **Saída**: Lista de destinos (`Destino[]`).

### Pesquisar Destinos

- **Rota**: `GET /destinos?nome=&localizacao=`
- **Entrada**: Parâmetros de busca.
- **Saída**: Lista de destinos correspondentes.

### Visualizar Destino

- **Rota**: `GET /destinos/{id}`
- **Saída**: Detalhes do destino.

### Avaliar Destino

- **Rota**: `PATCH /destinos/{id}/avaliacao`
- **Entrada**: Nota de avaliação.
- **Saída**: Nova média de avaliação.

### Reservar Pacote

- **Rota**: `POST /reservas`
- **Entrada**: Dados da classe `Reserva`.
- **Saída**: Confirmação da reserva.

### Excluir Destino

- **Rota**: `DELETE /destinos/{id}`
- **Saída**: Mensagem de confirmação.
