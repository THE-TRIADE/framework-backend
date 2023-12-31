INSERT INTO `user` (id, email, `password`, `name`, cpf, birthDate) VALUES 
    (1, 'ana@email.com', '$2a$10$RleiVtfl2ikXq1RYADYTC.rsUoA6eScRlk293ygGXzDjmFWE/yBQq', 'Ana Holanda', 99999999999, '2002-07-13'),
    (2, 'leandro@email.com', '$2a$10$/8XMU9zaTUp3Hbj9II1OxONTx7zvKaWzL7OW30JoikiDkopZqQOGu', 'Leandro Assunção', 88888888888, '2003-04-25');

INSERT INTO group_user_dependent (`name`) VALUES
    ('Família Sampaio'),
    ('Família Oliveira'),
    ('Família Boulevar');

INSERT INTO `dependent` (id, `name`, race, birthDate) VALUES 
    (3, 'Destruidor', 'puddle', '2022-07-13'),
    (4, 'Pãozinho', 'labrador', '2015-04-25'),
    (5, 'Pacificador', 'cão brabo', '2013-04-25');

INSERT INTO `dependent_group` (dependentId, groupId) VALUES
    (3, 3),
    (4, 3),
    (5, 2);

INSERT INTO `relation` (daysOfWeek, userRole, dependentId, userId) VALUES 
    ('SUNDAY,MONDAY', 'MOTHER', 4, 1),
    ('SUNDAY,MONDAY', 'MOTHER', 3, 1),
    ('SUNDAY,MONDAY', 'FATHER', 4, 2),
    ('SUNDAY,MONDAY', 'FATHER', 5, 2),
    (NULL, 'MOTHER', 5, 2);

INSERT INTO `category` (`name`) VALUES
    ('Saúde'),
    ('Limpeza'),
    ('Vacina'),
    ('Alimentação'),
    ('Lazer'),
    ('Outros');

INSERT INTO `activity` (`name`, dateStart, dateEnd, hourStart, hourEnd,`state`, dependentId, currentUserId, actorId, createdBy, categoryId) VALUES 
    ('Tomar banho', '2023-04-21', '2023-04-21', '20:00:00', '21:00:00', 'CREATED', 3, 1, 1, 1, 1),
    ('Pagar escola', '2023-04-21', '2023-04-21', '20:00:00', '21:00:00', 'CREATED', 3, 1, 1, 1, 6),
    ('Teste', '2023-05-18', '2023-05-21', '20:00:00', '21:00:00', 'CREATED', 5, 2, 2, 2, 1);

INSERT INTO `spent` (`name`, paidOn, `value`, activityId, dependentId, userId) VALUES 
    ('Odontologia', '2021-04-26', 100000, 1, 3, 1),
    ('Lazer', '2021-04-27', 100000, NULL, 3, 1);
