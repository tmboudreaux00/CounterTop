DROP DATABASE countertop_db;

CREATE DATABASE countertop_db;

DROP USER 'charles-countertop'@'localhost';

CREATE USER 'charles-countertop'@'localhost' IDENTIFIED BY 'password';

GRANT ALL ON countertop_db.* TO 'charles-countertop'@'localhost';
