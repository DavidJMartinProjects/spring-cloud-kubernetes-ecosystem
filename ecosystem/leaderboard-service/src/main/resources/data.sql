DROP TABLE IF EXISTS rankings;

CREATE TABLE rankings (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  country VARCHAR(250) NOT NULL,
  score INTEGER DEFAULT NULL,
  rank INTEGER DEFAULT NULL
);

INSERT INTO rankings (name, country, score, rank) VALUES
  ('Dave', 'Ireland', 10, 1),
  ('Bill', 'U.S.A', 8, 2),
  ('Folrunsho', 'U.K', 3, 3),
  ('Ted', 'U.K', 3, 4),
  ('JoeStump', 'U.K', 3, 5),
  ('DaveyWavey', 'U.K', 3, 6),
  ('Princess', 'U.K', 3, 7),
  ('LilDavey', 'U.K', 3, 8),
  ('SananChile', 'U.K', 3, 9),
  ('Maureen', 'U.K', 3, 10),
  ('Puskins', 'U.K', 3, 11);