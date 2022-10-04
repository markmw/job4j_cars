CREATE TABLE engine (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE car (
    id SERIAL PRIMARY KEY,
    name TEXT,
    engine_id INT NOT NULL UNIQUE REFERENCES engine(id)
);

CREATE TABLE driver (
    id SERIAL PRIMARY KEY,
    name TEXT,
    user_id INT NOT NULL UNIQUE REFERENCES auto_user(id)
);

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