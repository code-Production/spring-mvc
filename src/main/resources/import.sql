set search_path = "spring_shop";
-- drop schema if exists spring_db;
create schema if not exists spring_shop authorization postgres;
-- drop table if exists Orders;
-- drop table if exists Customers;
drop table if exists Users_Roles;
drop table if exists Roles;
drop table if exists Orders_Contents;
drop table if exists Products;
drop table if exists Orders;
drop table if exists Users;

create table Products (id bigserial primary key, title varchar(255), price real, created_at timestamp);
-- create table Customers (id bigserial primary key, name varchar(255));
-- create table Orders (id bigserial primary key, customer_id bigint, product_id bigint, price real, created_at TIMESTAMP);

create table Users(id bigserial primary key , username varchar(255) not null unique, password varchar(255) not null);

create table Roles(id bigserial primary key, name varchar(255) not null);

create table users_roles(user_id bigint not null, role_id bigint not null);

alter table Users_Roles add primary key (user_id, role_id);
alter table Users_Roles add constraint fk_users foreign key (user_id) references Users(id);
alter table Users_Roles add constraint fk_roles foreign key (role_id) references Roles(id);

-- alter table Orders add constraint fk_orders_products foreign key (product_id) references Products(id);
-- alter table Orders add constraint fk_orders_customers foreign key (customer_id) references Customers(id);
insert into Products (title, price, created_at) values ('Sugar', 90.0, now()), ('Milk', 70.0, now()), ('Eggs', 80.0, now()), ('Bread', 30.0, now()), ('Sugar', 40.0, now());
insert into Products (title, price, created_at) values ('Sugar2', 90.0, now()), ('Milk2', 70.0, now()), ('Eggs2', 80.0, now()), ('Bread2', 30.0, now()), ('Sugar2', 40.0, now());
insert into Products (title, price, created_at) values ('Sugar3', 90.0, now()), ('Milk3', 70.0, now()), ('Eggs3', 80.0, now()), ('Bread3', 30.0, now()), ('Sugar3', 40.0, now());
insert into Products (title, price, created_at) values ('Sugar4', 90.0, now());
-- insert into Customers (name) values ('Bob'), ('John'), ('Mike'), ('Kate');
-- insert into Orders (customer_id, product_id, price) VALUES (1,1,10), (1,2,20), (2,1,15), (2,2, 25), (3,1,5), (3,2,30), (4,1,0);

insert into Users (username, password) values ('log', '$2a$12$D4yxD6.nsKzupQOsRMzQn.9TBe69a10mqSkOVI2yf3db5NF9jLjFy');
insert into Users (username, password) values ('log2', '$2a$12$D4yxD6.nsKzupQOsRMzQn.9TBe69a10mqSkOVI2yf3db5NF9jLjFy');
insert into Users (username, password) values ('log3', '$2a$12$D4yxD6.nsKzupQOsRMzQn.9TBe69a10mqSkOVI2yf3db5NF9jLjFy');
insert into Roles (name) values ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_MANAGER'), ('ROLE_SUPERADMIN');
insert into users_roles (user_id, role_id) VALUES (1, 4);
insert into users_roles (user_id, role_id) VALUES (2, 2);
insert into users_roles (user_id, role_id) VALUES (3, 4);

CREATE TABLE IF NOT EXISTS Orders (id bigserial PRIMARY KEY, user_id bigint NOT NULL,address_id bigint,total double precision NOT NULL,created_at timestamp NOT NULL);
ALTER TABLE Orders ADD CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE;

CREATE TABLE IF NOT EXISTS Orders_Contents (id bigserial PRIMARY KEY,order_id bigint NOT NULL,product_id bigint NOT NULL,price double precision NOT NULL,amount integer NOT NULL);

ALTER TABLE Orders_Contents ADD CONSTRAINT fk_orders FOREIGN KEY (order_id) REFERENCES Orders(id) ON DELETE CASCADE;
ALTER TABLE Orders_Contents ADD CONSTRAINT fk_products FOREIGN KEY (product_id) REFERENCES Products(id) ON DELETE CASCADE;

insert into Orders (user_id, address_id, total, created_at) VALUES (1, null, 100, now());
insert into Orders_Contents (order_id, product_id, price, amount) VALUES (1, 1, 20, 1), (1, 2, 30 , 2), (1, 3, 40 , 3);









