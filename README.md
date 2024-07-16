# API de Fórum

## Descrição
Este projeto implementa uma API de Fórum utilizando Spring Boot para fornecer endpoints para gerenciar tópicos, respostas e usuários em um sistema de fórum.

## Documentação da API com Swagger
A documentação da API é gerada automaticamente utilizando o SpringDoc OpenAPI. Para acessar a documentação interativa da API, siga os passos abaixo:

1 - Após iniciar o projeto, abra um navegador web.
2 - Acesse o seguinte URL: `http://localhost:8080/swagger-ui/index.html#/

O Swagger UI permite explorar todos os endpoints disponíveis na API, seus parâmetros, respostas esperadas e exemplos de uso

## Dependências
- Spring Boot DevTools
- Lombok
- Spring Web
- Validation
- MySQL Driver
- Spring Data JPA
- Flyway Migration
- Spring Security
- JWT (JSON Web Token)
- SpringDoc OpenAPI

## Configuração do Ambiente
Certifique-se de ter o JDK 11 (ou superior) instalado e configurado em seu ambiente de desenvolvimento.

## Configuração do Banco de Dados
Este projeto utiliza o MySQL como banco de dados. Certifique-se de configurar corretamente as credenciais de acesso ao banco de dados no arquivo `application.properties`.

Exemplo de configuração do banco de dados:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
