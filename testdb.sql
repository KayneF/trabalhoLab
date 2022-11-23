DROP TABLE testdb;
CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;

CREATE TABLE IF NOT EXISTS produtos
	(id INTEGER AUTO_INCREMENT,
	item VARCHAR(20),
	marca VARCHAR(20),
	modelo VARCHAR(20),
	cor VARCHAR(20),
	specs VARCHAR(250),
	preco DOUBLE,
	PRIMARY KEY(id)
	);

INSERT INTO produtos (item, marca, modelo, cor, preco) VALUES ('GUITARRA', 'FENDER', 'STRATOCASTER', 'SUNBRUST', 12000.0);
INSERT INTO produtos (item, marca, modelo, cor, preco) VALUES ('GUITARRA', 'CORT', 'VIVA GOLD II', 'DOURADO', 6000.0);
INSERT INTO produtos (item, marca, modelo, cor, preco) VALUES ('BAIXO', 'IBANEZ', 'MAJESTY', 'PRATA', 15900.0);
INSERT INTO produtos (item, marca, modelo, cor, preco) VALUES ('TECLADO', 'KORG', 'M50', 'PRETO', 5000.0);
INSERT INTO produtos (item, marca, modelo, cor, preco) VALUES ('BATERIA', 'TAMA', 'T-5040', 'MADEIRA', 13600.0);
INSERT INTO produtos (item, marca, modelo, cor, preco) VALUES ('CABO', 'SANTO ANGELO', 'CB20', 'AZUL', 75.0);
INSERT INTO produtos (item, marca, modelo, cor, preco) VALUES ('PALHETA', 'TORTEX', 'TT11', 'ROXO', 3.0);
