create table products_carts(
    product_id              bigint,
    cart_id                 bigint,

    primary key(product_id),
    foreign key (product_id) references products (id),
    foreign key (cart_id) references carts (id)
);