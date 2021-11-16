CREATE SCHEMA test

create table test.users
(
	id serial,
	mail varchar(255) not null,
	first_name varchar(63) not null,
	last_name varchar(63) not null,
	patronymic varchar(63) not null,
	phone_number varchar(15) not null
);

create unique index users_id_uindex
	on test.users (id);

create unique index users_mail_uindex
	on test.users (mail);

create unique index users_phone_number_uindex
	on test.users (phone_number);

alter table test.users
	add constraint users_pk
		primary key (id);

INSERT INTO test.users (id, mail, first_name, last_name, patronymic, phone_number) VALUES
    (1, 'mail', 'first', 'last', 'patr', 'number')