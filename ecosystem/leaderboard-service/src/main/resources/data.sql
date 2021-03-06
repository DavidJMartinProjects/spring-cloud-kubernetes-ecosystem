DROP TABLE IF EXISTS leaderboard;

CREATE TABLE leaderboard (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  country VARCHAR(250) NOT NULL,
  score INTEGER DEFAULT NULL,
  rank INTEGER DEFAULT NULL
);

INSERT INTO leaderboard (name, country, score, rank) VALUES
  ('Dave', 'Ireland', 10, 1),
  ('Bill', 'U.S.A', 8, 5),
  ('Folrunsho', 'U.K', 3, 6);