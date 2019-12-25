DELETE FROM user_roles;
DELETE FROM user_votes;
DELETE FROM DISHES;
DELETE FROM users;
DELETE FROM RESTAURANTS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('User', 'user@yandex.ru', 'password'),
('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
('ROLE_USER', 100000),
('ROLE_ADMIN', 100001);

INSERT INTO restaurants (name) VALUES
('Burger King'),
('Mc Donalds');

INSERT INTO user_votes (vote_date, user_id, restaurant_id) VALUES
('2019-12-22 15:20:00', 100000, 100002),
('2019-12-22 16:20:00', 100000, 100003),
('2019-12-23 11:20:00', 100000, 100003),
('2019-12-23 12:20:00', 100000, 100003),
('2019-12-22 15:20:00', 100001, 100002);


INSERT INTO dishes (creation_date, name, price, restaurant_id) VALUES
('2019-12-22 15:20:00', 'vopper', 28050, 100002),
('2019-12-22 16:20:00', 'nuggets', 20000, 100002),
('2019-12-23 11:20:00', 'big king', 35000, 100002),
('2019-12-22 12:20:00', 'mac chicken', 25000, 100003),
('2019-12-23 15:20:00', 'mac free', 10000, 100003),
('2019-12-23 16:20:00', 'happy meal', 29000, 100003)