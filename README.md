# cadastro-cliente-api
API para cadastro de cliente e gerenciamento de dados para a Builders.

* O arquivo modelo_de_dados.drawio.png tem o desenho do modelo de dados para este sistema;
* O modelo criado, foi pensado para que o Cliente possa ter 1-n cotatos e endereços, sejam eles, comercial e residencial;
* O banco escolhido foi o MySQL;
* O empacotamento será um jar com o respectivo nome e contexto cadastro-cliente-api;
* Para o cadastro de Contato ou Endereco, deve existir, obrigatoriamente, um cliente cadastrado;
* A aplicação vai rodar em Docker com configuração bridge, JAVA 11, RDS (MYSQL) e EC2 micro;
* A configuração do ambiente está no docker-compose.yml.  

A inserção de informações estão isoladas, isto é, você pode inserir isoladamente o Cliente, o Contato e o Endereço.

Lembrando que para inserir o Contato ou Endereço, é necessário existir um Cliente.

A alteração também pode ser atualizada isoladamente.

Pelo Swagger, temos três conjuntos de APIs, para: Cliente, Contato e Contato Domínio + Endereço e Endereço Domínio. 

Redefinido a configuração JPA para EAGER, assim, a busca do Cliente já retorna o Contato e o Endereço.
