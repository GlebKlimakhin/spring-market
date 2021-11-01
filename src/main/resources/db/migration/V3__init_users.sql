
create table users (
                          id                          bigserial primary key,
                          username                    varchar(100),
                          password                    varchar(100),
                          created_at                  timestamp default current_timestamp,
                          updated_at                  timestamp default current_timestamp
);
