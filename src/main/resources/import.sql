/* Populate tabla clientes */

INSERT INTO regions (id, name) VALUES (1, 'America');
INSERT INTO regions (id, name) VALUES (2, 'Indian');
INSERT INTO regions (id, name) VALUES (3, 'Asia');
INSERT INTO regions (id, name) VALUES (4, 'Europe');
INSERT INTO regions (id, name) VALUES (5, 'Atlantic');
INSERT INTO regions (id, name) VALUES (6, 'Australia');
INSERT INTO regions (id, name) VALUES (7, 'Africa');
INSERT INTO regions (id, name) VALUES (8, 'Pacific');

INSERT INTO clients (region_id, name, alias, email, create_at) VALUES(1, 'Leno', 'Tavares', 'leno@gmail.com', '2019-01-01');
INSERT INTO clients (region_id, name, alias, email, create_at) VALUES(2, 'Mr. John', 'Doe', 'john.doe@gmail.com', '2019-01-02');
INSERT INTO clients (region_id, name, alias, email, create_at) VALUES(4, 'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2019-01-03');
INSERT INTO clients (region_id, name, alias, email, create_at) VALUES(4, 'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2019-01-04');
INSERT INTO clients (region_id, name, alias, email, create_at) VALUES(4, 'Erich', 'Gamma', 'erich.gamma@gmail.com', '2019-02-01');
INSERT INTO clients (region_id, name, alias, email, create_at) VALUES(3, 'Richard', 'Helm', 'richard.helm@gmail.com', '2019-02-10');
INSERT INTO clients (region_id, name, alias, email, create_at) VALUES(3, 'Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2019-02-18');
INSERT INTO clients (region_id, name, alias, email, create_at) VALUES(5, 'John', 'Vlissides', 'john.vlissides@gmail.com', '2019-02-28');
INSERT INTO clients (region_id, name, alias, email, create_at) VALUES(6, 'Dr. James', 'Gosling', 'james.gosling@gmail.com', '2019-03-03');
INSERT INTO clients (region_id, name, alias, email, create_at) VALUES(7, 'Magma', 'Lee', 'magma.lee@gmail.com', '2019-03-04');
INSERT INTO clients (region_id, name, alias, email, create_at) VALUES(8, 'Tornado', 'Roe', 'tornado.roe@gmail.com', '2019-03-05');
INSERT INTO clients (region_id, name, alias, email, create_at) VALUES(7, 'Jade', 'Doe', 'jane.doe@gmail.com', '2019-03-06');


/* Create some user with roles */
INSERT INTO `users` (username, password, enabled, name, alias, email) VALUES ('leno','$2a$10$Da52BJAnGsw9iGWmhQIwG.dtwY6XeK34FAoujzCs/tz5UkaM1Z6Jm',1, 'Leno', 'Tavares','profesor@bolsadeideas.com');
INSERT INTO `users` (username, password, enabled, name, alias, email) VALUES ('admin','$2a$10$Lwig8Mtme0EHvw7o5mODWeuib5QByRQxuwsiJSwB7dfYl1XOuaIMW',1, 'Joao', 'Eliene','jhon.doe@bolsadeideas.com');

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `users_authorities` (user_id, role_id) VALUES (1, 1);
INSERT INTO `users_authorities` (user_id, role_id) VALUES (2, 2);
INSERT INTO `users_authorities` (user_id, role_id) VALUES (2, 1);