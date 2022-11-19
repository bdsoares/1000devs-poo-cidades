CREATE DATABASE mesttra

CREATE TABLE cidades (
	ddd INT PRIMARY KEY,
	nome VARCHAR(50),
	nro_habitantes INT,
	renda_per_capita DECIMAL(10,2),
	capital BOOLEAN,
	estado CHAR(2),
	nome_prefeito VARCHAR(100)
)