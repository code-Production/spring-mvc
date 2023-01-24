set search_path = "spring_db";
-- drop schema if exists spring_db;
-- create schema if not exists spring_db authorization postgres;
drop table if exists spring_db.Orders;
drop table if exists spring_db.Products;
drop table if exists spring_db.Customers;
drop table if exists spring_db.Users_Roles;
drop table if exists spring_db.Users;
drop table if exists spring_db.Roles;
create table spring_db.Products (id bigserial primary key, title varchar(255), price real, created_at timestamp);
create table spring_db.Customers (id bigserial primary key, name varchar(255));
create table spring_db.Orders (id bigserial primary key, customer_id bigint, product_id bigint, price real, created_at TIMESTAMP);

create table spring_db.Users(id bigserial primary key , username varchar(255) not null unique, password varchar(255) not null);

create table spring_db.Roles(id bigserial primary key, name varchar(255) not null);

create table spring_db.users_roles(user_id bigint not null, role_id bigint not null);

alter table spring_db.Users_Roles add primary key (user_id, role_id);
alter table spring_db.Users_Roles add constraint fk_users foreign key (user_id) references spring_db.Users(id);
alter table spring_db.Users_Roles add constraint fk_roles foreign key (role_id) references spring_db.Roles(id);

alter table spring_db.Orders add constraint fk_orders_products foreign key (product_id) references spring_db.Products(id);
alter table spring_db.Orders add constraint fk_orders_customers foreign key (customer_id) references spring_db.Customers(id);
insert into spring_db.Products (title, price, created_at) values ('Sugar', 90.0, now()), ('Milk', 70.0, now()), ('Eggs', 80.0, now()), ('Bread', 30.0, now()), ('Sugar', 40.0, now());
-- insert into spring_db.Products (title, price, created_at) values ('Sugar2', 90.0, now()), ('Milk2', 70.0, now()), ('Eggs2', 80.0, now()), ('Bread2', 30.0, now()), ('Sugar2', 40.0, now());
-- insert into spring_db.Products (title, price, created_at) values ('Sugar3', 90.0, now()), ('Milk3', 70.0, now()), ('Eggs3', 80.0, now()), ('Bread3', 30.0, now()), ('Sugar3', 40.0, now());
-- insert into spring_db.Products (title, price, created_at) values ('Sugar4', 90.0, now());
insert into spring_db.Customers (name) values ('Bob'), ('John'), ('Mike'), ('Kate');
insert into spring_db.Orders (customer_id, product_id, price) VALUES (1,1,10), (1,2,20), (2,1,15), (2,2, 25), (3,1,5), (3,2,30), (4,1,0);

insert into spring_db.Users (username, password) values ('log', '$2a$12$NsHZqjIZ.kd4wh.uQehp3.juhslg9bz8XXYebFGDHHwvq7FHnABdW');
insert into spring_db.Users (username, password) values ('log2', '$2a$12$NsHZqjIZ.kd4wh.uQehp3.juhslg9bz8XXYebFGDHHwvq7FHnABdW');
insert into spring_db.Users (username, password) values ('log3', '$2a$12$NsHZqjIZ.kd4wh.uQehp3.juhslg9bz8XXYebFGDHHwvq7FHnABdW');
insert into spring_db.Roles (name) values ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_MANAGER'), ('ROLE_SUPERADMIN');
insert into spring_db.users_roles (user_id, role_id) VALUES (1, 1);
insert into spring_db.users_roles (user_id, role_id) VALUES (2, 2);
insert into spring_db.users_roles (user_id, role_id) VALUES (3, 4);










