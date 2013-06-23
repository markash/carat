CREATE TABLE IF NOT EXISTS item (
  id SERIAL UNIQUE,
  value VARCHAR(100),
  created TIMESTAMP NULL,
  updated TIMESTAMP NULL
);

INSERT INTO item (value) VALUES ('Test1');
INSERT INTO item (value) VALUES ('Test2');
INSERT INTO item (value) VALUES ('Test3');
INSERT INTO item (value) VALUES ('Test4');
INSERT INTO item (value) VALUES ('Test5');
INSERT INTO item (value) VALUES ('Test6');

