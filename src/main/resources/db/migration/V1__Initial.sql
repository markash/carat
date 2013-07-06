DROP TABLE IF EXISTS app_user_role;
DROP TABLE IF EXISTS app_role;
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS item;

CREATE TABLE IF NOT EXISTS item (
  id SERIAL UNIQUE PRIMARY KEY,
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

CREATE TABLE IF NOT EXISTS app_user (
  id SERIAL UNIQUE PRIMARY KEY,
  password TEXT NOT NULL,
  username TEXT NOT NULL,
  email TEXT NOT NULL,
  first_name TEXT NULL,
  last_name TEXT NULL,
  UNIQUE(username)
);

CREATE TABLE IF NOT EXISTS app_role (
  role TEXT UNIQUE
);

CREATE TABLE IF NOT EXISTS app_user_role (
  user_id INTEGER NOT NULL,
  user_role TEXT,
  UNIQUE(user_id, user_role),
  CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES app_user(id),
  CONSTRAINT fk_user_role_role FOREIGN KEY (user_role) REFERENCES app_role(role)
);

INSERT INTO app_role (role) VALUES ('EMPLOYEE');
INSERT INTO app_role (role) VALUES ('MANAGER');
INSERT INTO app_role (role) VALUES ('ADMIN');

INSERT INTO app_user (username, password, email, first_name, last_name) VALUES ('admin', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'admin@localhost', 'Administrator', 'User');

INSERT INTO app_user_role (user_id, user_role) values (currval('app_user_id_seq'), 'ADMIN');
