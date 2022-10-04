CREATE TABLE auto_user (
                       id SERIAL PRIMARY KEY,
                       login VARCHAR NOT NULL UNIQUE,
                       password VARCHAR NOT NULL
);

CREATE TABLE auto_post (
                     id SERIAL PRIMARY KEY,
                     text VARCHAR NOT NULL,
                     created TIMESTAMP NOT NULL,
                     auto_user_id INTEGER REFERENCES auto_user(id),
                     car_id INTEGER NOT NULL REFERENCES car(id)
);

create table engine(
    id serial primary key,
    name text
);

create table car(
    id serial primary key,
    name text,
    engine_id int not null unique references engine(id)
);

create table driver(
    id serial primary key,
    name text,
    user_id int not null unique references auto_user(id)
);

create table history_owner(
    id serial primary key,
    driver_id int not null references driver(id),
    car_id int not null references car(id),
    startAt varchar(255),
    endAt varchar(255)
);