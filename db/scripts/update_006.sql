CREATE TABLE history_owner (
    id SERIAL PRIMARY KEY,
    driver_id INT NOT NULL REFERENCES driver(id),
    car_id INT NOT NULL REFERENCES car(id),
    startAt VARCHAR(255),
    endAt VARCHAR(255)
);