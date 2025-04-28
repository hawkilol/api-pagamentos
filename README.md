# API Pagamentos
Esta API tem como objetivo possibilitar o recebimento e gerenciamento de pagamentos de débitos de pessoas físicas e jurídicas.

## Funcionalidades
> [!IMPORTANT]
> Endpoints documentados no Swagger (http://localhost:8080/swagger-ui/index.html)

### 1. Recebimento de Pagamentos
- #### /v1/pagamento
  Cria pagagamento (codigoDebito, cpfCnpj, metodoPagamento(id), valor, nCartao)
### 2. Atualização de Status de Pagamento
- #### /v1/pagamento-status/{pagamentoId}
  Atualiza Status por id
### 3. Listagem de Pagamentos
- #### /v1/pagamentos-filtar
  Paginação e ordenação pelos parametros do pageable
  Filtra por codigoDebito e/ou cpfCpnj e/ou status (body vazio {} para listar sem filtro)

### 4. Exclusão Lógica de Pagamentos
- #### /v1/pagamento/{pagamentoId}
  Desativar pagamento por id

### 5. Aux
- #### /v1/pagamento/{pagamentoId}
  Ler um pagamento por id
- #### /v1/pagamento-status-listar
  Lista os status disponíveis
- #### /v1/metodosPagamento
  Lista os metodos disponíveis 
  

## Versão compatível: JAVA 17+
  
## Build
```bash
cd mvn clean install
```
## Start dev
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

## test
  Testes do controller


## Dependências do Projeto
  Dependências principais:

- **Spring Boot**: Framework principal para construção de aplicações Java.
  - `spring-boot-starter-data-jpa`: Para integração query JPA e persistência de dados.
  - `spring-boot-starter-web`: Para criação de APIs REST.
  - `spring-boot-starter-validation`: Para validação de dados.

- **H2 Database**: Banco de dados em memória.

- **Lombok**: Reduzir o boilerplate (data, getter, setter, constructors, etc.).

- **Springdoc OpenAPI**: Documentação da API via Swagger.
  - `springdoc-openapi-starter-webmvc-ui`: Interface do Swagger.

<!-- - **ModelMapper**: apeamento de objetos Java, facilita o mapeamento entre DTOs e entities. -->
