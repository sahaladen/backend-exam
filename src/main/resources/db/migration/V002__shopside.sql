create table if not exists order(
    order_id bigint primary key,
    shipping_charge varchar(10),
    total_price varchar(10),
    shipping_status varchar(10),
    adress_id bigint
);

create sequence order_sql;

create table if not exists product(
    product_id bigint primary key,
    product_name varchar(100),
    description varchar(300),
    price varchar(10),
    status varchar(10),
    stock varchar(15)
);

create sequence product_sql;

create table order_product(
    order_id,
    product_id
);