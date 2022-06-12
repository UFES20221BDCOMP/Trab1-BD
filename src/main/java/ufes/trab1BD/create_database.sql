drop schema picpayDB;
create schema picpayDB;
use picpayDB;

create table user(
	user_id int not null auto_increment,
    name varchar(255) not null,
    date_of_birth date,
    balance float,
    primary key (user_id)
);

create table store(
	store_id int not null auto_increment,
    name varchar(255) not null,
    balance float,
    owner_id int,
    primary key (store_id)
);

create table purchase(
	user_id int,
    store_id int,
    value float,
    date date,
    primary key (user_id, store_id, date)
);

create table transfer(
	payer int,
    payee int,
    value float,
    date date,
    primary key (payer, payee, date)
);


alter table store 
add foreign key (owner_id) references user(user_id) on delete set null;

alter table purchase 
add foreign key (user_id) references user(user_id) on delete cascade;

alter table purchase
add foreign key (store_id) references store(store_id) on delete cascade;

alter table transfer
add foreign key (payer) references user(user_id) on delete cascade;

alter table transfer
add foreign key (payee) references user(user_id) on delete cascade;

insert into user (name, date_of_birth, balance) VALUES
("Danilo Erler Lima", "2001-03-03", 0),
("Enzo Baioco Cussuol", "2001-01-11", 99.99),
("Fernando Azevedo Peres", "2000-01-01", 0.99),
("Vitor Berger Bonella", "2001-06-18", 1000),
("Luis Eduardo da Camara  Freire", "2001-10-15", 50.0);


insert into store (name, balance, owner_id) VALUES
("Descartech", 0, 2),
("Little Monkeys", 99999, 3),
("A outra do REM", 0, 5),
("Esquentar Paozinho", 10.99,3);
