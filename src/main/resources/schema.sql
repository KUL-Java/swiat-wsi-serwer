CREATE SCHEMA swiat_wsi;
USE swiat_wsi;

CREATE TABLE villages
(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100),
  x INT,
  y INT,
  PRIMARY KEY (id)
);