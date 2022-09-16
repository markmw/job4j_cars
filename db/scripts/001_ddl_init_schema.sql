CREATE TABLE auto_user (
                       id SERIAL PRIMARY KEY,
                       login VARCHAR NOT NULL UNIQUE,
                       password VARCHAR NOT NULL
);

CREATE TABLE auto_post (
                     id SERIAL PRIMARY KEY,
                     text VARCHAR NOT NULL,
                     created TIMESTAMP NOT NULL,
                     auto_user_id INTEGER REFERENCES auto_user(id)
);