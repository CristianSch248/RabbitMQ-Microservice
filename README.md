# RabbitMQ Microservice

Projeto de estudo desenvolvido com **Spring Boot** e **RabbitMQ** para demonstrar a comunicação assíncrona entre microsserviços utilizando mensageria.

O projeto é composto por três módulos:

- **Producer**: recebe requisições HTTP e publica mensagens no RabbitMQ.
- **Consumer**: consome as mensagens publicadas e realiza o processamento.
- **Commons**: biblioteca compartilhada contendo os objetos utilizados na comunicação entre os microsserviços.

## Arquitetura

```text
                HTTP Request
                     │
                     ▼
              +---------------+
              |   Producer    |
              +---------------+
                     │
             ProductDTO (JSON)
                     │
                     ▼
              +---------------+
              |   RabbitMQ    |
              +---------------+
                     │
             ProductDTO (JSON)
                     │
                     ▼
              +---------------+
              |   Consumer    |
              +---------------+
```

---

## Estrutura do projeto

```text
rabbitmq-microservice
│
├── producer
│   └── API responsável por receber requisições HTTP e publicar mensagens.
│
├── consumer
│   └── Serviço responsável por consumir as mensagens do RabbitMQ.
│
└── commons
    └── Biblioteca compartilhada contendo DTOs e classes comuns.
```

---

## Tecnologias utilizadas

- Java 21
- Spring Boot 3
- Spring AMQP
- RabbitMQ
- Maven
- Docker
- Lombok

---

## Funcionamento

### Producer

O Producer expõe uma API REST que recebe um objeto `ProductDTO` via HTTP.

Exemplo de requisição:

```http
POST /product
Content-Type: application/json
```

```json
{
  "id": 1,
  "name": "Produto Exemplo",
  "price": 99.90
}
```

Após receber a requisição, o Producer converte automaticamente o objeto para JSON utilizando o `Jackson2JsonMessageConverter` e publica a mensagem no RabbitMQ.

---

### RabbitMQ

O RabbitMQ atua como intermediário entre os microsserviços, desacoplando o Producer do Consumer.

As mensagens são enviadas para uma **Direct Exchange**, que as encaminha para a fila configurada através da Routing Key.

---

### Consumer

O Consumer permanece escutando a fila através da anotação `@RabbitListener`.

Quando uma nova mensagem chega, ela é convertida automaticamente para um objeto `ProductDTO` e processada pela aplicação.

---

## Biblioteca Commons

O módulo **commons** é compartilhado entre os microsserviços e contém os objetos utilizados durante a comunicação.

Atualmente a biblioteca possui o DTO:

```java
public class ProductDTO implements Serializable {

    private Integer id;
    private String name;
    private Double price;

}
```

O uso de uma biblioteca compartilhada garante que Producer e Consumer utilizem exatamente a mesma estrutura de dados, evitando inconsistências durante a serialização e desserialização das mensagens.

---

## Executando o projeto

### 1. Suba o RabbitMQ

```bash
docker-compose up -d
```

---

### 2. Execute o módulo Consumer

O Consumer iniciará e ficará aguardando novas mensagens na fila.

---

### 3. Execute o módulo Producer

O Producer disponibilizará a API REST.

---

### 4. Envie uma requisição

```http
POST /product
```

```json
{
  "id": 1,
  "name": "Notebook",
  "price": 4500.00
}
```

---

## Objetivo

Este projeto tem como objetivo demonstrar:

- Comunicação assíncrona entre microsserviços
- Publicação e consumo de mensagens utilizando RabbitMQ
- Configuração do `Jackson2JsonMessageConverter`
- Compartilhamento de DTOs através de uma biblioteca comum
- Boas práticas na separação de responsabilidades entre Producer e Consumer