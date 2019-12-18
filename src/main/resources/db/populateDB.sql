DELETE FROM user_roles;
DELETE FROM user_votes;
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
('KFC');

INSERT INTO user_votes (vote_date, user_id, restaurant_id) VALUES
('2019-05-29 21:18:00', 100000, 100002),
('2019-05-28 22:05:00', 100000, 100003),
('2019-05-22 09:10:00', 100000, 100003),
('2019-05-23 10:25:00', 100001, 100003),
('2019-05-24 15:00:00', 100001, 100002);
