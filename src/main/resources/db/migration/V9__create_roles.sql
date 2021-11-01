create table roles(
    id                      serial primary key,
    name                    varchar(50) not null
);

insert into roles (name) values
('ROLE_USER'),
('ROLE_ADMIN'),
('ROLE_SUPERADMIN');