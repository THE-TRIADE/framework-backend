-- INSERT INTO `person` (`name`, cpf, birthDate) VALUES 
--     ('Ana Holanda', 99999999999, '2002-07-13'), 
--     ('Leandro Assunção', 88888888888, '2003-04-25'),
--     ('Joãozinho', 99999999999, '2012-07-13'),
--     ('Pedrinho', 88888888888, '2013-04-25'),
--     ('Josenildo', 11111111111, '2012-04-25');

INSERT INTO `user` (email, `password`, `name`, cpf, birthDate) VALUES 
    ('ana@email.com', '$2a$10$RleiVtfl2ikXq1RYADYTC.rsUoA6eScRlk293ygGXzDjmFWE/yBQq', 'Ana Holanda', 99999999999, '2002-07-13'),
    ('leandro@email.com', '$2a$10$/8XMU9zaTUp3Hbj9II1OxONTx7zvKaWzL7OW30JoikiDkopZqQOGu', 'Leandro Assunção', 88888888888, '2003-04-25');

INSERT INTO family_group (`name`) VALUES
    ('Família Sampaio'),
    ('Família Oliveira'),
    ('Família Bolevar');

-- INSERT INTO `dependent` (personId, familyGroupId) VALUES 
--     (3, 3),
--     (4, 3),
--     (5, 2);

-- INSERT INTO `guard` (daysOfWeek, guardianRole, dependentId, userId) 
--     VALUES ('SUNDAY,MONDAY', 'MOTHER', 3, 1),
--      ('SUNDAY,MONDAY', 'FATHER', 4, 2),
--      ('SUNDAY,MONDAY', 'MOTHER', 5, 2),
--      ('SUNDAY,MONDAY', 'FATHER', 4, 2);

-- INSERT INTO `guard` (guardianRole, dependentId, userId) VALUES 
--     ('MOTHER', 3, 2);

-- INSERT INTO `activity` (`name`, dateStart, dateEnd, hourStart, hourEnd,`state`, dependentId, currentUserId, actorId, createdBy) VALUES 
--     ('Tomar banho', '2023-04-21', '2023-04-21', '20:00:00', '21:00:00', 'CREATED', 3, 1, 1, 1),
--     ('Pagar escola', '2023-04-21', '2023-04-21', '20:00:00', '21:00:00', 'CREATED', 3, 1, 1, 1),
--     ('Teste', '2023-05-18', '2023-05-21', '20:00:00', '21:00:00', 'CREATED', 5, 2, 2, 2);

-- INSERT INTO `spent` (`name`, paidOn, `value`, activityId, dependentId, userId) VALUES 
--     ('Odontologia', '2021-04-26', 100000, 1, 3, 1);
-- INSERT INTO `spent` (`name`, paidOn, `value`, activityId, dependentId, userId) VALUES 
--     ('Lazer', '2021-04-27', 100000, NULL, 3, 1);
