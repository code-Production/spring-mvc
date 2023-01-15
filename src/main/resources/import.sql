-- drop schema if exists spring_db;
-- create schema if not exists spring_db authorization postgres;
drop table if exists spring_db.Products;
-- drop table if exists spring_db.Customers;
-- drop table if exists spring_db.Orders;
set search_path = "spring_db";
create table spring_db.Products (id bigserial primary key, title varchar(255), price real, created_at timestamp);
-- create table spring_db.Customers (id bigserial primary key, name varchar(255));
-- create table spring_db.Orders (id bigserial primary key, customer_id bigint, product_id bigint, price real, created_at TIMESTAMP);
-- alter table spring_db.Orders add constraint fk_orders_products foreign key (product_id) references Products(id);
-- alter table spring_db.Orders add constraint fk_orders_customers foreign key (customer_id) references Customers(id);
insert into spring_db.Products (title, price, created_at) values ('Sugar', 90.0, now()), ('Milk', 70.0, now()), ('Eggs', 80.0, now()), ('Bread', 30.0, now()), ('Sugar', 40.0, now());
insert into spring_db.Products (title, price, created_at) values ('Sugar2', 90.0, now()), ('Milk2', 70.0, now()), ('Eggs2', 80.0, now()), ('Bread2', 30.0, now()), ('Sugar2', 40.0, now());
insert into spring_db.Products (title, price, created_at) values ('Sugar3', 90.0, now()), ('Milk3', 70.0, now()), ('Eggs3', 80.0, now()), ('Bread3', 30.0, now()), ('Sugar3', 40.0, now());
insert into spring_db.Products (title, price, created_at) values ('Sugar4', 90.0, now());
-- insert into spring_db.Customers (name) values ('Bob'), ('John'), ('Mike'), ('Kate');
-- insert into spring_db.Orders (customer_id, product_id, price) VALUES (1,1,10), (1,2,20), (2,1,15), (2,2, 25), (3,1,5), (3,2,30), (4,1,0);