# API Java - Sistema de Compras e Eventos com JPA

Este projeto implementa um sistema básico em Java usando Spring Boot e JPA/Hibernate para gerenciamento de compras, eventos, ingressos e usuários. A API permite operações CRUD e modela relacionamentos entre entidades do domínio, como usuários que compram ingressos para eventos ou produtos em compras.

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- JPA/Hibernate
- MySQL (ou qualquer banco relacional)
- Maven
- Jakarta Persistence API (JPA)

## Entidades Principais

- **Compra**: Representa uma compra realizada por um usuário, contendo produtos, itens detalhados da compra e o valor total.
- **Evento**: Representa um evento (show, esportivo, cultural) com nome, descrição, data e quantidade de ingressos disponíveis.
- **Ingresso**: Representa a compra de ingressos para um evento por um usuário, armazenando quantidade de ingressos comprados.
- **ItensCompra**: Detalha os produtos comprados em uma compra, com quantidade e preço unitário.
- **Produto**: Representa um produto disponível para compra, com nome, preço e quantidade em estoque.
- **Usuario**: Representa o usuário do sistema, com nome, email, senha e tipo (VIP ou NORMAL).

## Funcionalidades

- CRUD completo para as entidades: `Compra`, `Evento`, `Ingresso`, `ItensCompra`, `Produto`, `Usuario`.
- Relacionamentos modelados:
  - Um **usuário** pode ter várias compras e ingressos.
  - Uma **compra** possui vários itens e produtos.
  - Um **ingresso** vincula um usuário a um evento.
- Uso de enums para tipos de evento e tipos de usuário para melhor organização e restrição de dados.

## Exemplo de Endpoints

```http
GET /usuarios           -> Lista todos os usuários
POST /usuarios          -> Cria novo usuário

GET /eventos            -> Lista todos os eventos
POST /eventos           -> Cria novo evento

GET /compras            -> Lista todas as compras
POST /compras           -> Registra nova compra

GET /ingressos          -> Lista ingressos comprados
POST /ingressos         -> Compra ingressos para evento
```

## Instalação

### Requisitos

- Java 17 ou superior
- Maven instalado
- MySQL instalado e rodando

### Passos para rodar o projeto

1. Clone o repositório:

```
git clone https://github.com/andreborgezz/prjFinalBackEnd.git
cd prjRenascev3
```

2. Configure o arquivo `application.properties` com os dados do seu banco de dados.

### Configuração do Banco de Dados (`application.properties`)

```properties
spring.application.name=prjRenascev3

spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/db_renasce?useSSL=false
spring.datasource.username=root
spring.datasource.password=12345

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.defer-datasource-initialization=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.jpa.hibernate.ddl-auto=update
```

> 💡 Altere os dados do banco conforme sua configuração local (ex: nome do banco, usuário e senha).

3. Compile e execute o projeto com Maven:
e estiver no Windows:

```cmd
mvn clean install
mvn spring-boot:run
```

---

