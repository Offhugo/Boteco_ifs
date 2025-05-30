# Sobre o projeto

O projeto consiste em um estudo realizado em sala de aula, no qual desenvolvemos um CRUD com o acompanhamento do professor em determinadas etapas do processo. 
O objetivo é compreender a estrutura do projeto, otimizar sua implementação e garantir o correto tratamento de dados para o uso eficiente dos componentes.

## Tecnologias envolvidas

  - Spring Boot
  - Spring Web
  - Lombok
  - Java 17
  - JPA / Hibernate
  - Banco H2
  - Bean validation (Jakarta)
  - ModelMapper
  - Maven

## Estrutura do projeto

- Controller
- Service
- Dtos
- Model
- Repository
- Config
- Exception
- Util
- application.properties

## Funcionalidades

- CRUD completo e funcional
- Validação com jakarta
- Tratamento de exceções qualificadas

## Principais EndPoints 

- GET /garcom/obter todo | obtem todos os garçons
- GET /garcom/find | obtem o garçom pelo nome
- POST /produto | salva um novo produto

## Aprendizados

- Arquiteturas de camadas
- Boas práticas de desenvolvimento
- tratamento de exceções
- Uso de DTO e Model Mapper
- Maven e suas configurações mais básicas

## Como rodar o projeto
git clone https://github.com/Offhugo/Boteco_ifs

### Acesse o diretório 
cd Boteco_ifs

### Rode a aplicação (Linux/macOS)
./mvnw spring-boot:run

### ou (Windows)
mvnw spring-boot:run

## Autor

  Hugo Mendes - Estudante de Ti e entusiasta do mundo tecnológico
