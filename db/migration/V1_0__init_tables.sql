create table users
(
	id serial,
	mail varchar(255) not null,
	first_name varchar(63) not null,
	last_name varchar(63) not null,
	patronymic varchar(63) not null,
	phone_number varchar(15) not null
);

create unique index users_id_uindex
	on users (id);

create unique index users_mail_uindex
	on users (mail);

create unique index users_phone_number_uindex
	on users (phone_number);

alter table users
	add constraint users_pk
		primary key (id);