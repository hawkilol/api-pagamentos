# API Pagamentos

## Versão compatível: JAVA 17
  
## Build
```bash
cd mvn clean install
```
## Start in
```bash
cd target/ && java -jar api-pagamentos-0.0.1-SNAPSHOT.jar
```

## Estrutura de diretórios
- ### documentation: 
  Pacote para configuração do Swagger

- ### domain 
  Pacote para domínio e lógica do negócio

  - #### exception
    Tratamento e Redirecionamento de exceções para o cliente

  - #### model
    Classes Entidades, mapear estrutura dos dados do Banco de Dados

  - #### service
    Lógica de negócio, operações entre models e repositories

  - #### repository
    Acesso aos dados, abstrai as interações com o Banco de Dados

  - #### specs
    Specifications (criterios de pesquisa) para filtros



- ### infraestructure
  Configurações internas da api

 
- ### v1
  Pacote para endpoints por versionamento

  - #### controller
    Gerencia as requisições HTTP, controla a lógica de I/O com o cliente, chama os services

  - #### dto
    Objetos para transferir dados da camada da API para as camadas de domain e model, expoe só os dados necessários e valida.


- ### resources
  Env de configuração e sql do projeto



## Dependências do Projeto
  Dependências principais:

- **Spring Boot**: Framework principal para construção de aplicações Java.
  - `spring-boot-starter-data-jpa`: Para integração com JPA e persistência de dados.
  - `spring-boot-starter-web`: Para criação de APIs RESTful.
  - `spring-boot-starter-validation`: Para validação de dados.

- **H2 Database**: Banco de dados em memória utilizado para desenvolvimento e testes.
  - `h2`: Banco de dados leve e embutido.

- **Lombok**: Biblioteca para reduzir o boilerplate no código Java (getter, setter, etc.).
  - `lombok`: Para geração automática de código.

- **Springdoc OpenAPI**: Para documentação automática da API via Swagger.
  - `springdoc-openapi-starter-webmvc-ui`: Para gerar a interface do Swagger.

- **ModelMapper**: Biblioteca para mapeamento de objetos Java.
  - `modelmapper`: Para facilitar o mapeamento entre DTOs e entidades.
