CREATE TABLE leituras (
  id INTEGER PRIMARY KEY autoincrement ,
  barras VARCHAR(45) NULL ,
  quantidade FLOAT NULL
  );

CREATE TABLE mercadorias (
  id INTEGER PRIMARY KEY autoincrement ,
  barras VARCHAR(45) NULL ,
  mercadoria VARCHAR(45) NULL ,
  preco VARCHAR(45) NULL
  );