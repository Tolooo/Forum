-- DROP TABLE UserCredentials;
CREATE TABLE UserCredentials (
  id       IDENTITY,
  email    VARCHAR2(30) NOT NULL,
  password VARCHAR2(30) NOT NULL,
  enabled  BOOL DEFAULT TRUE
);
INSERT INTO UserCredentials VALUES (1, 'a@aa.pl', 123123, TRUE);