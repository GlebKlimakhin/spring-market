
create table carts (
                       id                                              bigserial primary key,
                       user_id                                         bigint references users(id),
                        created_at                                     timestamp default current_timestamp,
                       updated_at                                      timestamp default current_timestamp
);
