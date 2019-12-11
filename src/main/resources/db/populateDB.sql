DELETE FROM user_roles;
DELETE FROM user_votes;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('User', 'user@yandex.ru', 'password'),
('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
('ROLE_USER', 100000),
('ROLE_ADMIN', 100001);

INSERT INTO user_votes (vote, user_id) VALUES
('FOUR', 100000),
('FIVE', 100000),
('FOUR', 100001),
('FOUR', 100001),
('ONE', 100001);
