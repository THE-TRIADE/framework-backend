CREATE TABLE `user`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL, 
    email VARCHAR(100), 
    PRIMARY KEY(id)
);

INSERT INTO `user` (id, `name`, email) VALUES (1, 'Ana Holanda', 'ana@email.com');
INSERT INTO `user` (id, `name`, email) VALUES (2, 'Leonardo Melo', 'leonardo@email.com');
INSERT INTO `user` (id, `name`, email) VALUES (3, 'Lucas Nogueira', 'lucas@email.com');