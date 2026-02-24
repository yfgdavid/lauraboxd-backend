Lauraboxd Backend
API backend do projeto Lauraboxd, construída com Java + Spring Boot, com persistência em PostgreSQL e autenticação via JWT.

Stack
Java + Spring Boot

Maven

PostgreSQL

JWT (token-based auth)

Deploy: Render (ou similar)

Estrutura do projeto
src/main/java/... — código fonte (controllers, services, repositories, security etc.)

src/main/resources/application.properties — configurações (sem segredos; tudo via env vars)

Pré-requisitos
Java (recomendado 17+)

Maven (ou usar o wrapper ./mvnw)

PostgreSQL (local ou Neon/Render)

Como rodar localmente
1) Configure variáveis de ambiente
Você pode exportar as env vars no terminal antes de rodar:

Windows PowerShell
$env:SPRING_DATASOURCE_URL="jdbc:postgresql://HOST:5432/DB"
$env:SPRING_DATASOURCE_USERNAME="USER"
$env:SPRING_DATASOURCE_PASSWORD="PASS"
$env:JWT_SECRET="uma_chave_forte"
$env:JWT_EXPIRATION="3600000"   # opcional (ms)
$env:PORT="8080"                # opcional
Observação: o projeto usa server.port=${PORT:8080} então, se PORT não existir, ele sobe na 8080.

2) Suba a aplicação
./mvnw spring-boot:run

ou

./mvnw clean package
java -jar target/*.jar
Variáveis de ambiente (produção)
Crie no Render (ou sua plataforma) as variáveis:

SPRING_DATASOURCE_URL

SPRING_DATASOURCE_USERNAME

SPRING_DATASOURCE_PASSWORD

JWT_SECRET

JWT_EXPIRATION (opcional)

PORT (normalmente a plataforma define automaticamente)

Boas práticas e segurança
Nunca commite credenciais em application.properties.

Não versionar target/ (Maven build output).

Se alguma credencial já foi exposta no histórico, rotacione senha/token imediatamente.

Rotas protegidas exigem Authorization: Bearer <token>

Contribuição
Faça um fork

Crie uma branch: git checkout -b feature/minha-feature

Commit: git commit -m "Minha feature"

Push: git push origin feature/minha-feature

Abra um Pull Request
