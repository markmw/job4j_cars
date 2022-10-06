create table auto_user(
    id serial primary key,
    login text,
    password text
);

create table auto_post(
    id serial primary key,
    text text,
    created timestamp,
    user_id int references auto_user(id)
);

CREATE TABLE price_history(
    id SERIAL PRIMARY KEY,
    before BIGINT not null,
    after BIGINT not null,
    created TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    p_user_id int references auto_user(id)
);

CREATE TABLE participates (
    id serial PRIMARY KEY,
    user_id int not null REFERENCES auto_user(id),
    post_id int not null REFERENCES auto_post(id)
);

create table engine (
    id serial primary key,
    name text
);

create table car (
    id serial primary key,
    name text,
    engine_id int references engine(id)
);

create table driver (
    id serial primary key,
    name text,
    user_id int references auto_user(id)
);

create table history_owner (
    id serial primary key,
    driver_id int references driver(id),
    car_id int references car(id)
);

