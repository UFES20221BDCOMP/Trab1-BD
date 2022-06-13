DROP SCHEMA picpayDB;
CREATE SCHEMA picpayDB;
USE picpayDB;

CREATE TABLE user(
	user_id int not null auto_increment,
    name varchar(255) not null,
    date_of_birth date,
    balance float,
    primary key (user_id)
);

CREATE TABLE store(
	store_id int not null auto_increment,
    name varchar(255) not null,
    balance float,
    owner_id int,
    primary key (store_id)
);

CREATE TABLE purchase(
	user_id int,
    store_id int,
    value float,
    date date,
    primary key (user_id, store_id, date)
);

CREATE TABLE transfer(
	payer int,
    payee int,
    value float,
    date date,
    primary key (payer, payee, date)
);


ALTER TABLE store 
ADD FOREIGN KEY (owner_id) REFERENCES user(user_id) ON DELETE set null;

ALTER TABLE purchase 
ADD FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE cascade;

ALTER TABLE purchase
ADD FOREIGN KEY (store_id) REFERENCES store(store_id) ON DELETE cascade;

ALTER TABLE transfer
ADD FOREIGN KEY (payer) REFERENCES user(user_id) ON DELETE cascade;

ALTER TABLE transfer
ADD FOREIGN KEY (payee) REFERENCES user(user_id) ON DELETE cascade;

ALTER TABLE user
ADD CHECK balance > 0;

ALTER TABLE store
ADD CHECK balance > 0;

INSERT INTO user (name, date_of_birth, balance) VALUES
("Danilo Erler Lima", "2001-03-03", 0),
("Enzo Baioco Cussuol", "2001-01-11", 99.99),
("Fernando Azevedo Peres", "2000-01-01", 0.99),
("Vitor Berger Bonella", "2001-06-18", 1000),
("Luis Eduardo da Camara Freire", "2000-11-29", 50.0),
("Igor Mattos Varejão","2001-01-28", 1001),
("Miguel de Oliveira Carlini", "2001-09-06", 300);


INSERT INTO store (name, balance, owner_id) VALUES
("Descartech", 0, 2),
("Little Monkeys", 99999, 3),
("A outra do REM", 0, 5),
("Esquentar Paozinho", 10.99,3);

UPDATE user SET name = "Luis Eduardo Freire da Camara" WHERE user_id = 5;
