INSERT INTO `user` (id, email, `password`, `name`, cpf, birthDate) VALUES 
    (1, 'ana@email.com', '$2a$10$RleiVtfl2ikXq1RYADYTC.rsUoA6eScRlk293ygGXzDjmFWE/yBQq', 'Ana Holanda', 99999999999, '2002-07-13'),
    (2, 'leandro@email.com', '$2a$10$/8XMU9zaTUp3Hbj9II1OxONTx7zvKaWzL7OW30JoikiDkopZqQOGu', 'Leandro Assunção', 88888888888, '1988-04-25'),
    (3, 'ivaldo@email.com', '$2a$10$RleiVtfl2ikXq1RYADYTC.rsUoA6eScRlk293ygGXzDjmFWE/yBQq', 'Ivaldo', 7777777777, '1970-07-13');

INSERT INTO group_user_dependent (`name`) VALUES
    ('Família Sampaio'),
    ('Família Oliveira'),
    ('Família Boulevar');

INSERT INTO `dependent` (id, `name`, cpf, birthDate) VALUES 
    (4, 'Joãozinho', '99999999999', '2012-07-13'),
    (5, 'Pedrinho', '88888888888', '2013-04-25'),
    (6, 'Josenildo', '11111111111', '2012-04-25'),
    (7, 'Ana Filho', '22222222222', '2011-05-15'),
    (8, 'Lucas', '33333333333', '20015-04-12'),
    (9, 'Carlos', '4444444444', '20018-04-12');

INSERT INTO `dependent_group` (dependentId, groupId) VALUES
    (4, 1),
    (5, 1),
    (6, 2),
    (7, 2),
    (8, 3),
    (9, 3);

INSERT INTO `relation` (daysOfWeek, userRole, dependentId, userId) VALUES 
    ('SUNDAY,MONDAY', 'MOTHER', 4, 1),
    ('SUNDAY,MONDAY', 'FATHER', 4, 3),
    ('SUNDAY,MONDAY', 'MOTHER', 5, 1),
    ('SUNDAY,MONDAY', 'MOTHER', 6, 1),
    ('SUNDAY,MONDAY', 'MOTHER', 7, 1),
    ('SUNDAY,MONDAY', 'FATHER', 8, 2),
    (NULL, 'FATHER', 9, 2);

INSERT INTO `activity` (`name`, dateStart, dateEnd, hourStart, hourEnd,`state`, dependentId, currentUserId, actorId, createdBy) VALUES 
    ('Tomar banho', '2023-04-21', '2023-04-21', '20:00:00', '21:00:00', 'CREATED', 4, 3, 4, 1),
    ('Tarefa de casa', '2023-07-22', '2023-07-22', '18:00:00', '19:00:00', 'CREATED', 4, 3, 4, 3),
    ('Natação', '2023-07-21', '2023-07-21', '10:00:00', '11:00:00', 'CREATED', 4, 3, 4, 1),
    ('Natação', '2023-07-23', '2023-07-21', '10:00:00', '11:00:00', 'CREATED', 4, 1, 4, 1),
    ('Natação', '2023-07-25', '2023-07-25', '10:00:00', '11:00:00', 'CREATED', 4, 1, 4, 1),

    ('Tomar banho', '2023-04-21', '2023-04-21', '20:00:00', '21:00:00', 'CREATED', 5, 1, 5, 1),
    ('Pagar escola', '2023-04-21', '2023-04-21', '20:00:00', '21:00:00', 'CREATED', 5, 1, 1, 1),
    ('Teste', '2023-05-18', '2023-05-21', '20:00:00', '21:00:00', 'CREATED', 6, 2, 6, 2);

INSERT INTO `spent` (`name`, paidOn, `value`, activityId, dependentId, userId) VALUES 
    ('Viagem para Camurupim', '2021-04-25', 100000, NULL, 4, 2),
    ('Odontologia', '2021-04-26', 937, NULL, 4, 1),
    ('Disney', '2020-06-26', 13450, NULL, 4, 1),
    ('Ovo da páscoa', '2023-04-03', 100, NULL, 4, 1),
    ('Viagem para Camurupim', '2021-04-27', 100000, NULL, 4, 2),
    ('Disney 2', '2020-06-26', 13450, NULL, 5, 1),
    ('Ovo da páscoa', '2023-04-03', 100, NULL, 5, 1),
    ('Roupa', '2023-04-03', 100, NULL, 5, 1),
    ('Viagem para Camurupim', '2021-04-27', 100000, NULL, 5, 2);