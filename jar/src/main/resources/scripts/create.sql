create table IF NOT EXISTS USER(
	id bigint auto_increment PRIMARY KEY,
	name varchar(25) ,
	password varchar(25)
);
commit;

		merge into user key(id,name,password) values (1,'user','user');
		merge into user key(id,name,password) values (2,'admin','admin');
commit;


create table IF NOT EXISTS SNIPPET(
	id bigint auto_increment PRIMARY KEY,
	username SMALLINT ,
	text CLOB
);
commit;

create table IF NOT EXISTS BOOK(
	nameBook varchar(50) ,
	ISBN varchar(50),
	nameAuthor varchar(50)
);
commit;

create table IF NOT EXISTS AUTHOR(
	nameAuthor varchar(50) ,
	dateOfBirth date
);
commit;
