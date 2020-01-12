CREATE DATABASE testdb;
CREATE USER app WITH PASSWORD 'app';
GRANT ALL PRIVILEGES ON DATABASE testdb TO app;
\connect app
CREATE TABLE public.userlist
(
    id serial NOT NULL,
    username text NOT NULL,
    firstname text NOT NULL,
    lastname text NOT NULL,
    age integer NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_username_key UNIQUE (username)
);
ALTER TABLE public.userlist
    OWNER to app;
