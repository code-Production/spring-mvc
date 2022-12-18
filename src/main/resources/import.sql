--drop schema if exists spring_db;
--create schema if not exists spring_db authorization postgres;
drop table if exists spring_db.Products;
create table spring_db.Products (id bigserial primary key, title varchar(255), price real);
insert into spring_db.Products (title, price) values ('Sugar', 90.0), ('Milk', 70.0), ('Eggs', 80.0), ('Bread', 30.0), ('Sugar', 40.0);