## 🎬 Lauraboxd Backend API

API backend do **Lauraboxd**, uma aplicação para registrar, organizar e compartilhar resenhas de filmes e séries.

O projeto foi desenvolvido com **Java + Spring Boot**, persistência em **PostgreSQL** e autenticação **JWT (token-based)**, seguindo boas práticas de arquitetura REST.

---

## 🚀 Stack Tecnológica

* **Java 17+**
* **Spring Boot** (Web, Data JPA, Security)
* **PostgreSQL** (produção e desenvolvimento)
* **JWT** para autenticação e autorização
* **Maven** para gerenciamento de dependências
* **Deploy:** Render / Neon (ou qualquer plataforma compatível)

---

## 📂 Estrutura do Projeto

```
src/
 └── main/
     ├── java/
     │   └── com.example.lauraboxd/
     │       ├── controller/   # Endpoints REST
     │       ├── service/       # Regras de negócio
     │       ├── repository/    # Repositórios JPA
     │       ├── model/          # Entidades
     │       ├── dto/            # DTOs de requisição/resposta
     │       └── security/       # Configuração JWT e Spring Security
     └── resources/
         └── application.properties  # Configurações via ENV
```

> ⚠️ Nenhuma credencial é versionada. Todas as configurações sensíveis são feitas via variáveis de ambiente.

---

## ⚙️ Pré-requisitos

* **Java 17 ou superior**
* **Maven** (ou Maven Wrapper `./mvnw`)
* **PostgreSQL** (local) ou banco gerenciado (Neon / Render)

---

## ▶️ Como Rodar Localmente

### 1️⃣ Configure as variáveis de ambiente

#### Windows (PowerShell)


$env:SPRING_DATASOURCE_URL="jdbc:postgresql://HOST:5432/DB"
$env:SPRING_DATASOURCE_USERNAME="USER"
$env:SPRING_DATASOURCE_PASSWORD="PASS"
$env:JWT_SECRET="uma_chave_super_secreta"
$env:JWT_EXPIRATION="3600000" # opcional (ms)
$env:PORT="8080"              # opcional
```

#### Linux / macOS


export SPRING_DATASOURCE_URL=jdbc:postgresql://HOST:5432/DB
export SPRING_DATASOURCE_USERNAME=USER
export SPRING_DATASOURCE_PASSWORD=PASS
export JWT_SECRET=uma_chave_super_secreta
export JWT_EXPIRATION=3600000
export PORT=8080
```

> O projeto usa `server.port=${PORT:8080}` — se `PORT` não for definido, a aplicação sobe na porta **8080**.

---

### 2️⃣ Subir a aplicação


./mvnw spring-boot:run
```

ou


./mvnw clean package
java -jar target/*.jar
```

---

## 🌐 Variáveis de Ambiente (Produção)

Configure na plataforma de deploy (Render, Railway, etc.):

* `SPRING_DATASOURCE_URL`
* `SPRING_DATASOURCE_USERNAME`
* `SPRING_DATASOURCE_PASSWORD`
* `JWT_SECRET`
* `JWT_EXPIRATION` (opcional)
* `PORT` (geralmente definido automaticamente)

---

## 🔐 Autenticação

* Rotas protegidas exigem header:

```http
Authorization: Bearer <SEU_TOKEN_JWT>
```

* Tokens são gerados após login e possuem tempo de expiração configurável.

---

## 🧪 Boas Práticas de Segurança

* ❌ Nunca commitar credenciais no repositório
* ❌ Não versionar a pasta `target/`
* 🔁 Rotacionar senhas/tokens caso sejam expostos
* 🔐 Usar variáveis de ambiente em todos os ambientes

---

## 🤝 Contribuição

1. Faça um **fork** do projeto
2. Crie uma branch:


git checkout -b feature/minha-feature
```

3. Commit suas alterações:


git commit -m "Minha feature"
```

4. Envie para o repositório remoto:


git push origin feature/minha-feature
```

5. Abra um **Pull Request** 🚀

---

## 📌 Autor

**David Victor** – Desenvolvedor Back-end & Full Stack em formação

Projeto pessoal criado para aprendizado e portfólio.
