# API de Clientes

Documentação resumida dos endpoints disponíveis na aplicação.

## URL base

```text
http://localhost:8080
```

As requisições que possuem corpo devem utilizar o cabeçalho:

```http
Content-Type: application/json
```

## Clientes

### Cadastrar cliente

```http
POST /clientes
```

O cliente deve ser cadastrado com pelo menos um endereço. O CPF não pode estar associado a outro cliente.

Exemplo de corpo:

```json
{
  "nome": "Maria da Silva",
  "cpf": "12345678900",
  "email": "maria@email.com",
  "telefone": "11999999999",
  "enderecos": [
    {
      "cep": "01001-000",
      "rua": "Praça da Sé",
      "numero": "100",
      "bairro": "Sé",
      "cidade": "São Paulo",
      "estado": "SP"
    }
  ]
}
```

Resposta de sucesso: `201 Created`.

### Listar clientes

```http
GET /clientes
```

Retorna todos os clientes com seus respectivos endereços.

Resposta de sucesso: `200 OK`.

### Buscar cliente por ID

```http
GET /clientes/{id}
```

Exemplo:

```http
GET /clientes/2
```

Resposta de sucesso: `200 OK`.

Caso o cliente não exista, retorna `404 Not Found`.

### Atualizar cliente

```http
PUT /clientes/{id}
```

Exemplo de corpo:

```json
{
  "nome": "Maria Oliveira",
  "cpf": "12345678900",
  "email": "maria.oliveira@email.com",
  "telefone": "11988888888"
}
```

O CPF pode continuar sendo o CPF atual do cliente, mas não pode pertencer a outro cliente.

Resposta de sucesso: `200 OK`.

### Excluir cliente

```http
DELETE /clientes/{id}
```

Exemplo:

```http
DELETE /clientes/2
```

A exclusão do cliente também exclui seus endereços.

Resposta de sucesso: `204 No Content`.

## Endereços

### Adicionar endereço a um cliente

```http
POST /clientes/{clienteId}/enderecos
```

Exemplo de corpo:

```json
{
  "cep": "01310-100",
  "rua": "Avenida Paulista",
  "numero": "1500",
  "bairro": "Bela Vista",
  "cidade": "São Paulo",
  "estado": "SP"
}
```

Resposta de sucesso: `201 Created`.

Caso o cliente não exista, retorna `404 Not Found`.

### Atualizar endereço

```http
PUT /enderecos/{id}
```

O parâmetro `{id}` é o ID do endereço, não o ID do cliente.

Exemplo de corpo:

```json
{
  "cep": "01310-200",
  "rua": "Avenida Paulista",
  "numero": "2000",
  "bairro": "Bela Vista",
  "cidade": "São Paulo",
  "estado": "SP"
}
```

Resposta de sucesso: `200 OK`.

Caso o endereço não exista, retorna `404 Not Found`.

### Excluir endereço

```http
DELETE /enderecos/{id}
```

Exemplo:

```http
DELETE /enderecos/3
```

O parâmetro informado deve ser o ID do endereço. Um cliente não pode ficar sem endereço; por isso, o último endereço não pode ser excluído.

Resposta de sucesso: `204 No Content`.

Se for o único endereço do cliente, retorna `400 Bad Request`.

## Códigos de resposta

| Código | Significado |
| --- | --- |
| `200 OK` | Consulta ou atualização realizada com sucesso |
| `201 Created` | Recurso cadastrado com sucesso |
| `204 No Content` | Recurso excluído com sucesso |
| `400 Bad Request` | Dados inválidos ou violação de regra de negócio |
| `404 Not Found` | Cliente ou endereço não encontrado |

## Exemplo de erro de regra de negócio

```json
{
  "erro": "Regra de negócio",
  "mensagem": "O cliente não pode ficar sem endereço.",
  "dataHora": "2026-09-27T14:23:52.3424577",
  "status": 400
}
```
