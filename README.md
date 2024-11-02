# Aggregate API

## Descrição
Esta API é projetada para unificar e consolidar dados de várias fontes em um único endpoint, padronizando e facilitando o acesso a informações integradas e consistentes.

## Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 3.3.5**
- **Spring Data JPA**
- **Spring Web**
- **Spring Kafka**
- **MySQL**
- **MapStruct**
- **Lombok**
- **Springdoc OpenAPI UI**

## Pré-requisitos
- **Java Development Kit (JDK) 21** instalado.
- **Apache Maven** para gerenciamento de dependências e construção do projeto.
- Um banco de dados **MySQL** em funcionamento.

## Configuração do Banco de Dados
1. Crie um banco de dados no MySQL que será utilizado pela aplicação.
2. Configure as credenciais de acesso ao banco de dados no arquivo `application.properties` (ou `application.yml`), adicionando as seguintes propriedades:
   ```properties
   spring.datasource.url=jdbc:mysql://<HOST>:<PORT>/<DATABASE_NAME>
   spring.datasource.username=<USERNAME>
   spring.datasource.password=<PASSWORD>
   ```
   Substitua `<HOST>`, `<PORT>`, `<DATABASE_NAME>`, `<USERNAME>` e `<PASSWORD>` pelas informações correspondentes do seu banco de dados MySQL.

## Como Rodar o Projeto
1. Clone este repositório:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd aggregate-api
   ```
2. Compile o projeto com Maven:
   ```bash
   mvn clean install
   ```
3. Inicie a aplicação:
   ```bash
   mvn spring-boot:run
   ```


## Testes
Para rodar os testes automatizados do projeto, utilize o seguinte comando:
```bash
mvn test
```

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença
Este projeto está licenciado sob a MIT License - consulte o arquivo LICENSE para mais detalhes.
