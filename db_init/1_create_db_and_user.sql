CREATE DATABASE currency_service_db;
CREATE USER 'spring'@'%' IDENTIFIED BY '123456';
GRANT ALL ON currency_service_db.* TO 'spring'@'%';