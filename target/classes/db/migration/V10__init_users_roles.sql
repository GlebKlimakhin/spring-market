create table users_roles(
    user_id             bigint not null,
    role_id             int not null,

    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2),
(5, 3);