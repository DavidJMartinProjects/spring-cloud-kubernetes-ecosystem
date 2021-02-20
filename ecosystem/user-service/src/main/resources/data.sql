DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  firstname VARCHAR(250) NOT NULL,
  lastname VARCHAR(250) NOT NULL,
  score INTEGER DEFAULT NULL,
  rank INTEGER DEFAULT NULL
);

INSERT INTO users (firstname, lastname, score, rank) VALUES
  ('Dave', 'Dangote', 10, 1),
  ('Bill', 'Gates', 8, 5),
  ('Folrunsho', 'Alakija', 3, 6);