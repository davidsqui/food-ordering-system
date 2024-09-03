INSERT INTO restaurant.restaurants (id, name, active)
VALUES ('f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b31', 'restaurant_1', true);
INSERT INTO restaurant.restaurants (id, name, active)
VALUES ('f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b32', 'restaurant_2', false);

INSERT INTO restaurant.products (id, name, price, available)
VALUES ('f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b41', 'product_1', 25.00, false);
INSERT INTO restaurant.products (id, name, price, available)
VALUES ('f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b42', 'product_2', 50.00, true);
INSERT INTO restaurant.products (id, name, price, available)
VALUES ('f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b43', 'product_3', 20.00, false);
INSERT INTO restaurant.products (id, name, price, available)
VALUES ('f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b44', 'product_4', 40.00, true);

INSERT INTO restaurant.restaurant_products (id, restaurant_id, product_id)
VALUES ('f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b51', 'f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b31', 'f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b41');
INSERT INTO restaurant.restaurant_products (id, restaurant_id, product_id)
VALUES ('f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b52', 'f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b31', 'f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b42');
INSERT INTO restaurant.restaurant_products (id, restaurant_id, product_id)
VALUES ('f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b53', 'f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b32', 'f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b43');
INSERT INTO restaurant.restaurant_products (id, restaurant_id, product_id)
VALUES ('f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b54', 'f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b32', 'f4b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b44');