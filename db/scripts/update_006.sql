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

CREATE TABLE history_owner (
    id SERIAL PRIMARY KEY,
    driver_id INT NOT NULL REFERENCES driver(id),
    car_id INT NOT NULL REFERENCES car(id),
    startAt VARCHAR(255),
    endAt VARCHAR(255)
);