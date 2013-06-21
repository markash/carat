CREATE TABLE IF NOT EXISTS item (
  id BIGINT IDENTITY NOT NULL PRIMARY KEY,
  value VARCHAR(100),
  create_ts TIMESTAMP NULL,
  update_ts TIMESTAMP NULL
);

INSERT INTO item (value) VALUES ('Test1');
INSERT INTO item (value) VALUES ('Test2');
INSERT INTO item (value) VALUES ('Test3');
INSERT INTO item (value) VALUES ('Test4');
INSERT INTO item (value) VALUES ('Test5');
INSERT INTO item (value) VALUES ('Test6');

