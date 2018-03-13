CREATE TABLE customers (
  customer_id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE orders (
  order_id BIGSERIAL PRIMARY KEY,
  item_name VARCHAR(255),
  quantity INT,
  payment_method VARCHAR(255),
  customer_id BIGINT REFERENCES customers
);
