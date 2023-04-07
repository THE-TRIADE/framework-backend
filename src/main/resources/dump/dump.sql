CREATE TABLE guardian (
    id BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    birthDate DATE NOT NULL,
    email VARCHAR(100) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO guardian (id, `name`, cpf, birthDate, email, `password`) VALUES (1, 'Ana Holanda', 99999999999, '2002-07-13','ana@email.com', 'ana123');
INSERT INTO guardian (id, `name`, cpf, birthDate, email, `password`) VALUES (2, 'Leandro Assuncao', 88888888888, '2003-04-25','leandro@email.com', 'leandro123');


-- CREATE TABLE `user`(
--     id BIGINT NOT NULL AUTO_INCREMENT,
--     `name` VARCHAR(100) NOT NULL, 
--     email VARCHAR(100), 
--     PRIMARY KEY(id)
-- );

-- INSERT INTO `user` (id, `name`, email) VALUES (1, 'Ana Holanda', 'ana@email.com');
-- INSERT INTO `user` (id, `name`, email) VALUES (2, 'Leonardo Melo', 'leonardo@email.com');
-- INSERT INTO `user` (id, `name`, email) VALUES (3, 'Lucas Nogueira', 'lucas@email.com');