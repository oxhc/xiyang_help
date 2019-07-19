create database esbase;
use esbase;
create table sc (
	id int auto_increment,
	name varchar(30),
	info varchar(500),
    birth date,
    vts varchar(300),
    primary key(id)
);
create table vt (
	id int auto_increment,
	user varchar(20),
	name varchar(30),
	info varchar(500),
	passwd varchar(90),
 	primary key(id)
);
create table favor (
	id int auto_increment, 
	vtid int,
	scid int,
	info varchar(350),
	PRIMARY KEY(id)
);