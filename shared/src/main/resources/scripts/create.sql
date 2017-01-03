create table IF NOT EXISTS USER(
	id bigint auto_increment PRIMARY KEY,
	user varchar(25) ,
	password varchar(25)
);


create table IF NOT EXISTS AUTHOR(
	id bigint auto_increment PRIMARY KEY,
	name varchar(25) ,
	book bigint,
	 FOREIGN KEY (books) REFERENCES BOOKS(id)
);

create table IF NOT EXISTS AUTHOR(
	id bigint auto_increment PRIMARY KEY,
	title varchar(25) ,
	isbn varchar(25),
	yearEdition int,
	creationDate date
);
