# DailySinc

DailySinc é uma aplicação **Spring Boot RESTful** desenvolvida para gerenciar relatórios diários de desenvolvedores, suas equipes e autenticação de usuários.  
O sistema segue o padrão **Spring MVC**, com camadas bem definidas de controller, service, repository e model.

---

## 🧩 Visão Geral

A aplicação oferece uma API para controle de:
- **Developers** — Cadastro, atualização e remoção de desenvolvedores.
- **Teams** — Gerenciamento de times e associações.
- **Dailies** — Registro e consulta de relatórios diários.
- **Auth** — Autenticação e registro de usuários com JWT.

---

## 🚀 Tecnologias

- **Java 17+**
- **Spring Boot 3+**
- **Spring Security (JWT)**
- **Spring Data JPA**
- **Maven**
- **Jakarta Validation**
- **Lombok**

---

## ⚙️ Executando o projeto

### 1. Clone o repositório
```bash
git clone https://github.com/vinehds/DailySinc.git
cd DailySinc
```

### 2. Compile e instale dependências
```bash
./mvnw clean install
```

### 3. Execute a aplicação
```bash
./mvnw spring-boot:run
```
A API será iniciada em: **http://localhost:8080**

---

## 🔐 Autenticação

A autenticação utiliza **JWT (JSON Web Token)**.  
Endpoints públicos: `/auth/login` e `/auth/register`.

### Exemplos:

#### Login
```http
POST /auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "123456"
}
```
**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

#### Registro
```http
POST /auth/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "123456",
  "role": "USER"
}
```

#### Usuário Logado
```http
GET /auth/me
Authorization: Bearer <token>
```

---

## 📚 Endpoints Principais

### 🧍 Developers (`/developers`)
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| GET | `/developers` | Lista todos os desenvolvedores |
| GET | `/developers/{id}` | Busca um desenvolvedor por ID |
| POST | `/developers` | Cria um novo desenvolvedor |
| PUT | `/developers/{id}` | Atualiza um desenvolvedor existente |
| DELETE | `/developers/{id}` | Remove um desenvolvedor |

### 👥 Teams (`/teams`)
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| GET | `/teams` | Lista todas as equipes |
| GET | `/teams/{id}` | Busca uma equipe por ID |
| POST | `/teams` | Cria uma nova equipe |
| PUT | `/teams/{id}` | Atualiza uma equipe |
| DELETE | `/teams/{id}` | Remove uma equipe |

### 📅 Dailies (`/dailies`)
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| GET | `/dailies` | Lista todos os relatórios diários |
| GET | `/dailies/byDate?date=dd/MM/yyyy` | Filtra relatórios por data |
| GET | `/dailies/{id}` | Busca um relatório por ID |
| POST | `/dailies` | Cria um novo relatório diário |
| PUT | `/dailies/{id}` | Atualiza um relatório existente |
| DELETE | `/dailies/{id}` | Remove um relatório |

---

## 🧠 Estrutura do Projeto

```
src/
 ├── main/java/com/vinehds/dailysinc/
 │    ├── controller/        # Controladores REST
 │    ├── service/           # Lógica de negócio
 │    ├── model/             # Entidades e DTOs
 │    ├── repository/        # Interfaces JPA
 │    └── infra/security/    # Configuração de segurança e JWT
 └── resources/
      ├── application.yml    # Configurações do Spring Boot
      └── ...
```

---

## 🧪 Testes

Execute os testes com:
```bash
./mvnw test
```

---

## 🧩 Próximas Features

- Dashboard com métricas de produtividade.  
- Envio automático de reports diários.  
- Integração com Slack/Discord.  
- Documentação com Swagger/OpenAPI.

---

## 🧾 Licença

Este projeto é distribuído sob a licença **MIT**.  
Sinta-se à vontade para usar e contribuir!

---

**Autor:** [Vinícius Dalaqua](https://github.com/vinehds)
