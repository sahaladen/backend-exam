create table if not exists order(
    order_id bigint primary key,
    shipping_charge int,
    total_price int,
    shipping_status varchar(12),
    customer_id
);

create sequence order_sql;

create table if not exists product(
    product_id bigint primary key,
    product_name varchar(100),
    description varchar(300),
    price int,
    status varchar(10),
    stock int
);

create sequence product_sql;

create table order_product(
    order_id,
    product_id
);