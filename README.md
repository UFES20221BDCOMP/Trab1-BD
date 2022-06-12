# Trabalho 01 - Banco de Dados

Feito pelos alunos: 
  Danilo Erler Lima ([@daniloelima](https://github.com/daniloelima))
  Enzo Baioco Cussuol ([@enzocussuol](https://github.com/enzocussuol))
  
O projeto envolve a criação de uma API-REST envolvendo o uso de um banco de dados em mysql, tendo sido inspirado no [desafio picpay](https://github.com/PicPay/picpay-desafio-backend), embora não tenha sido seguido a risca.


## Banco de Dados

Organizamos o banco de dados conforme o [diagrama entidade relacionamento](https://github.com/UFES20221BDCOMP/Trab1-BD/blob/main/documentation/diagrama_entidade_relacionamento_picpay.pdf) e obtemos as seguintes tabelas:

***
user(<ins>user_id</ins>, name, date_of_birth, balance)

store(<ins>store_id</ins>, name, balance, owner_id)

transfer(<ins>payer</ins>, <ins>payee</ins>,  <ins>date</ins>, value)

purchase(<ins>user_id</ins>, <ins>store_id</ins>, <ins>date</ins>, value)
***

## API-REST

Dentre as requisições projetadas pela API temos:

Referentes aos usuários
```
    /users                             -> Retorna todos usuários do banco, ordenados por id
    /users/{user_id}                   -> Retorna ados do usuários especificado
    /users/{name}                      -> Retorna lista de usuários com nome especificado
    /users/{user_id}/purchases         -> Retorna todas compras realizadas pelo usuario especificado
    /users/{user_id}/transfers         -> Retorna todas transferencias realizadas e recebidas pelo usuario especificado
    /users/{user_id}/transactions      -> Retorna todas compras e transferencias realizadas e recebidas pelo usuario especificado
    /users/{user_id}/stores            -> Retorna lista das lojas do usuario especificado
    
    /users/new?{params}                -> Insere um novo usuário no banco
    /users/{user_id}/update?{params}   -> Atualiza um usuario do banco
```

Referentes as Lojas
```
    /stores                             -> Retorna todas lojas do banco, ordenadas por id
    /stores/{store_id}                  -> Retorna dados da loja especificada
    /stores/{store_id}/purchases        -> Retorna todas compras feitas na loja
    
    /stores/new?{params}                -> Insere uma nova loja no banco
    /stores/{store_id}/update?{params}  -> Atualiza uma loja do banco
```

Referentes as transações e compras
```
    /transfer/{payer}/{payee}/{value}   -> Realiza uma transferencia de um usuário para outro
    /purchase/{params}                  -> Realiza uma compra de um usuário para uma loja
```


Outras requisições
```
    /richest                            -> Retorna dados do usuário e de suas empresas, com maior valor total envolvido 
    /money                              -> Retorna valor total de dinheiro envolvido no banco (soma de todos user.balance e store.balance)
```
