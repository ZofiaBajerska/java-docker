CREATE DATABASE testdb;
CREATE USER app WITH PASSWORD 'app';
GRANT ALL PRIVILEGES ON DATABASE testdb TO app;
\c testdb app
CREATE TABLE userlist
(
    id serial NOT NULL,
    username text NOT NULL,
    firstname text NOT NULL,
    lastname text NOT NULL,
    age integer NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_username_key UNIQUE (username)
);
ALTER TABLE userlist
    OWNER to app;
