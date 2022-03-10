# cadastro-cliente-api
API para cadastro de cliente e gerenciamento de dados para a Builders.

* O arquivo modelo_de_dados.drawio.png tem o desenho do modelo de dados para este sistema;

![alt text](https://github.com/itimes-digital/cadastro-cliente-api/blob/main/modelo_de_dados.drawio.png)

* O modelo criado, foi pensado para que o Cliente possa ter 1-n cotatos e endereços, sejam eles, comercial e residencial;
* O banco escolhido foi o MySQL;
* O empacotamento será um jar com o respectivo nome e contexto cadastro-cliente-api;
* Para o cadastro de Contato ou Endereco, deve existir, obrigatoriamente, um cliente cadastrado;
* A aplicação vai rodar em Docker com configuração bridge, JAVA 11, RDS (MYSQL) e EC2 micro;
* A configuração do ambiente está no docker-compose.yml;
* A inserção de informações estão isoladas, isto é, você pode inserir isoladamente o Cliente, o Contato e o Endereço;
* Pelo Swagger, temos 4 conjuntos de APIs: Cliente, Contato, Endereço e Domínios (Contato e Endereço);
* Na implementação do modelo de dados foi redefinido a configuração JPA para EAGER, além de inserção de outros atributos, assim, a busca do Cliente já retorna o Contato e o Endereço, caso exista.

A collections do Postman com todos os serviços: REST API Cadastro Cliente Builders.postman_collection.json

Segue URL do Swagger da aplicação: http://ec2-3-90-107-210.compute-1.amazonaws.com:8080/swagger-ui.html

* Usuário: admin
* Senha: cadastro-cliente-api

Alguns conjuntos de dados para testes

* Cliente completo com Contato e Endereço:

Deve-se preencher o CPF ou o CNPJ, um dos dois são obrigatórios.
```json
{
  "contato": {
    "contatoEnum": "CONTATO_RESIDENCIAL",
    "email": "elvis@gmail.com",
    "numTelefone": "11969236896"
  },
  "cpf": "274.839.880-70",
  "endereco": {
    "caixaPostal": "12583 - SP",
    "cidade": "São Paulo",
    "enderecoEnum": "ENDERECO_RESIDENCIAL",
    "estado": "SP",
    "logradouro": "Av Wolfgang Amadeu Mozart",
    "numCep": "02101000",
    "numLogradouro": 1756,
    "pais": "BR"
  },
  "dataNascimento": "1979-10-05",
  "nickname": "thepelvis",
  "primeiroNome": "Elvis",
  "segundoNome": "Aaron Presley",
  "tipoPessoa": "F"
}

{
  "contato": {
    "contatoEnum": "CONTATO_COMERCIAL",
    "email": "tony@gmail.com",
    "numTelefone": "11969236896"
  },
  "cpf": "270.000.000-00",
  "endereco": {
    "caixaPostal": "12583 - SP",
    "cidade": "São Paulo",
    "enderecoEnum": "ENDERECO_COMERCIAL",
    "estado": "SP",
    "logradouro": "Av Wolfgang Amadeu Mozart",
    "numCep": "02101000",
    "numLogradouro": 1756,
    "pais": "BR"
  },
  "dataNascimento": "1979-10-05",
  "nickname": "tony",
  "primeiroNome": "Tony",
  "segundoNome": "Bennet",
  "tipoPessoa": "F"
}
```
* Somente Endereço de um Cliente existente:
```json
{
    "idCliente": 1,
    "cidade": "São Paulo",
    "enderecoEnum": "ENDERECO_COMERCIAL",
    "estado": "SP",
    "logradouro": "Av James Brown",
    "numCep": "02101999",
    "numLogradouro": 1933,
    "pais": "BR"
}
```
* Domínios de Contato e Endereço:
```json
{
  "descDominio": "Tipo de endereço comercial",
  "nomeDominio": "ENDERECO_COMERCIAL"
}

{
  "descDominio": "Tipo de endereço residencial",
  "nomeDominio": "ENDERECO_RESIDENCIAL"
}

{
  "descDominio": "Tipo de contato comercial",
  "nomeDominio": "CONTATO_COMERCIAL"
}

{
  "descDominio": "Tipo de contato residencial",
  "nomeDominio": "CONTATO_RESIDENCIAL"
}
```

