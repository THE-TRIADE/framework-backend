INSERT INTO `person` (`name`, cpf, birthDate) VALUES 
    ('Ana Holanda', 99999999999, '2002-07-13'), 
    ('Leandro Assuncao', 88888888888, '2003-04-25'),
    ('Joaozinho', 99999999999, '2012-07-13'),
    ('Pedrinho', 88888888888, '2013-04-25');

INSERT INTO guardian (email, `password`, personId) VALUES 
    ('ana@email.com', '$2a$10$RleiVtfl2ikXq1RYADYTC.rsUoA6eScRlk293ygGXzDjmFWE/yBQq', 1),
    ('leandro@email.com', '$2a$10$/8XMU9zaTUp3Hbj9II1OxONTx7zvKaWzL7OW30JoikiDkopZqQOGu', 2);

INSERT INTO family_group (`name`) VALUES
    ('familia Sampaio'),
    ('familia Oliveira'),
    ('familia Bolevar');

INSERT INTO `dependent` (personId, familyGroupId) VALUES 
    (3, 1),
    (4, 2),
    (1, 2);

INSERT INTO `guard` (daysOfWeek, guardianRole, dependentId, guardianId) 
    VALUES ('SUNDAY,MONDAY', 'MOTHER', 3, 1);
INSERT INTO `guard` (guardianRole, dependentId, guardianId) VALUES 
    ('MOTHER', 3, 2);

INSERT INTO `activity` (`name`, dateStart, dateEnd, hourStart, hourEnd,`state`, familyGroupId, dependentId, currentGuardianId, actorId, createdBy) VALUES 
    ('Tomar banho', '2023-04-21', '2023-04-21', '20:00:00', '21:00:00', 'CREATED', 3, 1, 1, 1, 1),
    ('Pagar escola', '2023-04-21', '2023-04-21', '20:00:00', '21:00:00', 'CREATED', 3, 1, 1, 1, 1);
