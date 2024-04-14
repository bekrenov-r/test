drop table if exists telegram_user cascade;
create table telegram_user(
    id serial primary key,
    telegram_id text unique,
    first_name text,
    last_name text,
    username text,
    created_at timestamp
);

drop table if exists post cascade;
create table post
(
    id         uuid,
    user_id    text,
    emoji_id   int,
    created_at timestamp,
    foreign key (user_id) references telegram_user (telegram_id)
);

