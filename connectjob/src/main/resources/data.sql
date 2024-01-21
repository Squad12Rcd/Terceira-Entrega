INSERT INTO usuario (nome, senha) VALUES ('admin@admin.com',  '$2a$10$JYTd7UC9bEhoJTLHXqmmm.q5fTuN4cSQu2iUbtKeCLLDcTbbJEfky');

INSERT INTO role (authority) VALUES ('ROLE_USUARIO');
INSERT INTO role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO role (authority) VALUES ('ROLE_EMPRESA');


INSERT INTO usuario_role (usuario_id, role_id) VALUES (1, 2);