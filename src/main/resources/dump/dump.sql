CREATE TABLE `guardian` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    birthDate DATE NOT NULL,
    email VARCHAR(100) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);
CREATE TABLE `dependent` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    birthDate DATE NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO guardian (`name`, cpf, birthDate, email, `password`) VALUES ('Ana Holanda', 99999999999, '2002-07-13','ana@email.com', 'ana123');
INSERT INTO guardian (`name`, cpf, birthDate, email, `password`) VALUES ('Leandro Assuncao', 88888888888, '2003-04-25','leandro@email.com', 'leandro123');

INSERT INTO `dependent` (`name`, cpf, birthDate) VALUES ('Joaozinho', 99999999999, '2012-07-13');
INSERT INTO `dependent` (`name`, cpf, birthDate) VALUES ('Pedrinho', 88888888888, '2013-04-25');