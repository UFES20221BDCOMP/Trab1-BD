# Trabalho 01 - Banco de Dados

Feito pelos alunos: 
  Danilo Erler Lima ([@daniloelima](https://github.com/daniloelima))
  Enzo Baioco Cussuol ([@enzocussuol](https://github.com/enzocussuol))
  
O projeto envolve a criação de uma API-REST envolvendo o uso de um banco de dados, tendo sido inspirado no [desafio picpay](https://github.com/PicPay/picpay-desafio-backend), embora não tenha sido seguido a risca.

## Tecnologias Utilizadas

Para o desenvolvimento da API-REST, utilizamos a linguagem Java e o framework Spring-Boot. Para organização do projeto foi utilizado o Maven.

Com relação ao banco de dados, utilizamos o MYSQL.

A conexão entre a aplicação e o banco é feita através de consultas nativas, as quais podem ser encontradas no pacote "repo" dentro do código-fonte.

## Execução

Para executar a API primeiro é necessário [baixar a aplicação](https://github.com/UFES20221BDCOMP/Trab1-BD/archive/refs/heads/main.zip) e extrair na pasta desejada, após isso executar no terminal (dentro da pasta deszipada) o comando:

```
docker-compose up
```

Esse comando irá gerar os dois containers do sistema, o da aplicação e o do banco de dados MYSQL. Ele pode demorar pois irá baixar todas as dependências para o Maven e para o MYSQL funcionarem, além de fazer o build da aplicação.

Após o comando ser executado, a aplicação e o banco de dados estarão de pé. Note que nós executamos um script de criação das tabelas e preenchimento de alguns dados no banco logo após ele ser inicializado.

Para acessar a aplicação, basta acessar no navegador a página de documentação da nossa API, a qual irá mostrar todas as requisições que são possíveis de serem realizadas e o que cada uma faz:

```
http://localhost:8080/swagger-ui/index.html
```

Essa é uma página de documentação interativa gerada a partir da biblioteca [springdoc](https://springdoc.org/).

Nós encorajamos o uso da API a partir da página de documentação, uma vez que a experiência torna-se mais intuitiva. Porém, também preparamos alguns testes unitários básicos utilizando o [jUnit](https://junit.org/junit5/). Esses testes podem ser executados diretamente no container da aplicação. Para isso, rode em outro terminal o comando:

```
docker exec -it trab1-bd-10_spring-boot-app_1 mvn test
```

## Banco de Dados

Organizamos o banco de dados conforme o [diagrama entidade relacionamento](https://github.com/UFES20221BDCOMP/Trab1-BD/blob/main/documentation/diagrama_entidade_relacionamento_picpay.pdf) e obtemos as seguintes tabelas:

***
user(<ins>user_id</ins>, name, document, date_of_birth, balance)

store(<ins>store_id</ins>, name, balance, owner_id)

transfer(<ins>transfer_id</ins>, payer, payee,  date, value)

purchase(<ins>purchase_id</ins>, user_id, store_id, date, value)
***

## API-REST

Dentre as requisições projetadas pela API temos:

Referentes aos usuários
```
    /users/create                                       -> Insere um novo usuário no banco
    /users/read                                         -> Retorna todos usuários do banco
    /users/read/{user_id}                               -> Retorna dados do usuários especificado
    /users/update/{user_id}/updateBalance/{value}       -> Atualiza o saldo do usuario especificado (insere se > 0  remove se < 0)
    /users/delete/{user_id}                             -> Remove um usuario do banco
```

Referentes as lojas
```
    /stores/create                                      -> Insere uma nova loja no banco
    /stores/read                                        -> Retorna todas lojas do banco
    /stores/read/{store_id}                             -> Retorna dados da loja especificada
    /stores/readByOwner/{owner_id}                      -> Retorna todas as lojas com id do dono especificado
    /stores/delete/{store_id}                           -> Remove uma loja do banco
```

Referentes as transações 
```
    /transfer/create                                    -> Realiza uma transferencia de um usuário para outro
    /transfer/read                                      -> Retorna todas as transferencias do banco
    /transfer/read/{transfer_id}                        -> Retorna dados da transferencia especificada
    /transfer/cancel/{transfer_id}                      -> Cancela uma transferencia, retornando os valores envolvidos
```

Referentes as compras
```
    /purchase/create                                    -> Realiza uma compra de um usuário para outro
    /purchase/read                                      -> Retorna todas as compras do banco
    /purchase/read/{purchase_id}                        -> Retorna dados da compra especificada
    /purchase/cancel/{purchase_id}                      -> Cancela uma compra, retornando os valores envolvidos
```

Outras requisições
```
    /richest                            -> Retorna dados do usuário e de suas empresas, com maior valor total envolvido 
    /money                              -> Retorna valor total de dinheiro envolvido no banco (soma de todos user.balance e store.balance)
```
