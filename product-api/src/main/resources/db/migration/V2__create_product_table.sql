create table products.product(
    id bigserial primary key,
    product_indentifier varchar not null,
    name varchar(100) not null,
    descricao varchar not null,
    preco float not null,
    category_id bigint REFERENCES products.category(id)
)