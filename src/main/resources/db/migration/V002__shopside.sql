create table if not exists customer_order(
    order_id bigint primary key,
    shipping_charge int,
    total_price int,
    shipping_status varchar(12),
    customer_id bigint
);

create sequence customer_order_sql;

create table if not exists product(
    product_id bigint primary key,
    product_name varchar(100),
    description varchar(300),
    price int,
    status varchar(15),
    stock int
);

create sequence product_sql;

create table if not exists order_product(
    order_id bigint,
    product_id bigint
);

