
# Atividade JDBC

Este projeto se trata de um exercício proposto no programa 1000 Devs 2022.
## v1

- Construa sua fábrica de conexões e estabeleça conexão com o banco de dados mildevs, teste essa conexão utilizando um programa principal.

- Represente a tabela cidade(ddd*, nome, nro_habitantes, renda_per_capita, capital**, estado, nome_prefeito) com um POJO.

        *PK
        **capital é um booleano.

- Crie um DAO para cidades e crie um método de inserção de cidades

- Crie no seu DAO um método de remoção por DDD.

- Crie no seu programa principal menu para cadastro de cidades, que peça ao usuário todos os dados e que no fim o insira no banco de dados, este programa deve avaliar se os dados inseridos correspondem ao tipo pedido e tratar este comportamento. Também será possível remover uma cidade pelo DDD através dessa interface..

## v2

- Crie no seu CidadeDAO o método de listagem de cidades sem filtros.

- Crie no seu DAO um método que é capaz de retornar uma cidade com base no seu número de ddd.

- Crie um método que é capaz de pesquisar cidades cujos nomes se iniciam por um texto passado como parâmetro.

- Crie um método que é capaz de listar cidades filtradas pela sigla de estado.

- Crie um método que recebe a sigla de um estado e retorna a quantidade de cidades daquele estado.

- Provavelmente neste momento você já repetiu bastante o while (rs.next) e a construção da cidade, que tal criar um método privado que seja genérico e utilizá-lo em seus outros métodos de listagem?

- Crie um método que filtra cidades pela coluna capital, onde o valor do filtro é passado como parâmetro.

- Modifique seu menu (Programa Principal) para ser capaz de utilizar as novas funcionalidades.
## Variáveis de Ambiente

Para executar este projeto, você precisará adicionar as seguintes variáveis ​​de ambiente ao seu arquivo .env

`POSTGRES_USER`

`POSTGRES_PASS`


## JDBC Driver

Necessário importar o JDBC Driver do PostgreSQL no projeto.

[Download | pgJDBC](https://jdbc.postgresql.org/download/)

Utilizada versão `42.5.0`
