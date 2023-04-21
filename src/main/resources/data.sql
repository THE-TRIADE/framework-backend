INSERT INTO `person` (`name`, cpf, birthDate) VALUES ('Ana Holanda', 99999999999, '2002-07-13');
INSERT INTO `person` (`name`, cpf, birthDate) VALUES ('Leandro Assuncao', 88888888888, '2003-04-25');

INSERT INTO `person` (`name`, cpf, birthDate) VALUES ('Joaozinho', 99999999999, '2012-07-13');
INSERT INTO `person` (`name`, cpf, birthDate) VALUES ('Pedrinho', 88888888888, '2013-04-25');

INSERT INTO guardian (email, `password`, personId) VALUES ('ana@email.com', '$2a$10$RleiVtfl2ikXq1RYADYTC.rsUoA6eScRlk293ygGXzDjmFWE/yBQq', 1);
INSERT INTO guardian (email, `password`, personId) VALUES ('leandro@email.com', '$2a$10$/8XMU9zaTUp3Hbj9II1OxONTx7zvKaWzL7OW30JoikiDkopZqQOGu', 2);

INSERT INTO `dependent` (personId) VALUES (3);
INSERT INTO `dependent` (personId) VALUES (4);

INSERT INTO `activity` (`name`,`description`, dateStart, dateEnd, hourStart, hourEnd,`state`,commentary,dependentId,currentGuardianId,actorId,createdBy) VALUES ('Tomar banho', null, '2023-04-21', '2023-04-21', '20:00:00', '21:00:00', 'CREATED', null, 3, 1, 3, 1);
INSERT INTO `activity` (`name`,`description`, dateStart, dateEnd, hourStart, hourEnd,`state`,commentary,dependentId,currentGuardianId,actorId,createdBy) VALUES ('Pagar escola', null, '2023-04-21', '2023-04-21', '20:00:00', '21:00:00', 'CREATED', null, 3, 1, 1, 1);