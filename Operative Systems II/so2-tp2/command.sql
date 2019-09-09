DROP TABLE IF EXISTS alergenios CASCADE;
CREATE TABLE alergenios (
  id TEXT
);

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
  username TEXT,
  password text,
  email text
);

DROP TABLE IF EXISTS useral CASCADE;
CREATE TABLE useral (
  username TEXT,
  id TEXT
);

DROP TABLE IF EXISTS registos CASCADE;
CREATE TABLE registos (
  username TEXT,
  id TEXT,
  data TEXT,
  codigo TEXT,
  latitude TEXT,
  longitude TEXT
);



insert into alergenios values('platano');
insert into alergenios values('gramineas');
insert into alergenios values('oliveira');
insert into alergenios values('azinheira');
