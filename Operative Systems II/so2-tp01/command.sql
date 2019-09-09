DROP TABLE IF EXISTS questionarios CASCADE;
CREATE TABLE questionarios (
  id TEXT,
  numPerguntas INTEGER,
  vezesRespondido INTEGER,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS pergunta CASCADE;
CREATE TABLE pergunta (
  id TEXT,
  pergunta TEXT,
  numero INTEGER,
  FOREIGN KEY (id) REFERENCES questionarios(id),
  PRIMARY KEY (id, numero)
);

DROP TABLE IF EXISTS resposta CASCADE;
CREATE TABLE resposta (
    codigounico SERIAL,
    id TEXT,
    resposta INTEGER,
    FOREIGN KEY (id) REFERENCES questionarios(id),
    PRIMARY KEY(codigounico)
);

INSERT INTO questionarios 
	VALUES ('QUEST1',0,0);

INSERT INTO questionarios 
	VALUES ('QUEST2',0,0);

INSERT INTO questionarios
	VALUES ('CRONICASEDA2',0,0);
