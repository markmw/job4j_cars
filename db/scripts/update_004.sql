CREATE TABLE PRICE_HISTORY(
   id SERIAL PRIMARY KEY,
   before BIGINT not null,
   after BIGINT not null,
   created TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);

ALTER TABLE auto_post ADD COLUMN price_history_id INT REFERENCES price_history(id);