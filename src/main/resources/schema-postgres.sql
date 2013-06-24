CREATE TABLE IF NOT EXISTS item (
  id SERIAL UNIQUE,
  description VARCHAR(100),
  created TIMESTAMP NULL,
  updated TIMESTAMP NULL
);

INSERT INTO item (description) VALUES ('Test1');
INSERT INTO item (description) VALUES ('Test2');
INSERT INTO item (description) VALUES ('Test3');
INSERT INTO item (description) VALUES ('Test4');
INSERT INTO item (description) VALUES ('Test5');
INSERT INTO item (description) VALUES ('Test6');

