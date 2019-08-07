INSERT INTO Role(id, name) VALUES (1,'admin');
INSERT INTO Role(id, name) VALUES (2,'user');

INSERT INTO User (id, login, password, email) VALUES (1, 'admin', 'admin', 'test@mail.ru');
INSERT INTO User (id, login, password, email) VALUES (2, 'user', 'user', 'user@mail.ua');

INSERT INTO users_has_roles(users_id, roles_id) VALUES (2, 2);
INSERT INTO users_has_roles(users_id, roles_id) VALUES (1, 1);





