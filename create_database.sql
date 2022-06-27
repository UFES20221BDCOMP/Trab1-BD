DROP SCHEMA picpayDB;
CREATE SCHEMA picpayDB;
USE picpayDB;

CREATE TABLE user(
	user_id int not null auto_increment,
    document varchar(255) not null unique,
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
    purchase_id int not null auto_increment,
	user_id int,
    store_id int,
    value float,
    date date,
    primary key (purchase_id)
);

CREATE TABLE transfer(
    transfer_id int not null auto_increment,
	payer_id int,
    payee_id int,
    value float,
    date date,
    primary key (transfer_id)
);

ALTER TABLE store
ADD FOREIGN KEY (owner_id) REFERENCES user(user_id);

ALTER TABLE purchase
ADD FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE;

ALTER TABLE purchase
ADD FOREIGN KEY (store_id) REFERENCES store(store_id) ON DELETE CASCADE;

ALTER TABLE transfer
ADD FOREIGN KEY (payer_id) REFERENCES user(user_id) ON DELETE CASCADE;

ALTER TABLE transfer
ADD FOREIGN KEY (payee_id) REFERENCES user(user_id) ON DELETE CASCADE;

INSERT INTO user (document, name, date_of_birth, balance) VALUES
("123.456.789-13", "Danilo Erler Lima", "2001-03-03", 0),
("432.231.432-21", "Enzo Baioco Cussuol", "2001-01-11", 99.99),
("652.342.543-32", "Fernando Azevedo Peres", "2000-01-01", 0.99),
("456.654.064-03", "Vitor Berger Bonella", "2001-06-18", 1000),
("432.566.777-12", "Luis Eduardo da Camara Freire", "2000-11-29", 50.0),
("435.059.032-21", "Igor Mattos Varejão","2001-01-28", 1001),
("054.693.401-33", "Miguel de Oliveira Carlini", "2001-09-06", 300);

INSERT INTO store (name, balance, owner_id) VALUES
("Descartech", 0, 2),
("Little Monkeys", 99999, 3),
("A outra do REM", 0, 5),
("Esquentar Paozinho", 10.99,3);