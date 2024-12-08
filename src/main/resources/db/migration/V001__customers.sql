create table if not exists customer(
    customer_id bigint primary key,
    first_name varchar(100),
    last_name varchar(100),
    phone_number varchar(30),
    email varchar(100)
);

create sequence customer_sql;

create table if not exists customer_address(
    address_id bigint primary key,
    address varchar(200)
);

create sequence customer_address_sql;

create table if not exists customer_address_customer(
    customer_id bigint,
    address_id bigint
);