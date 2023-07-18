INSERT INTO `user` (id, email, `password`, `name`, cpf, birthDate, `role`) VALUES 
    (1, 'ana@email.com', '$2a$10$RleiVtfl2ikXq1RYADYTC.rsUoA6eScRlk293ygGXzDjmFWE/yBQq', 'Ana Holanda', 99999999999, '2002-07-13', 'PRINCIPAL'),
    (2, 'leandro@email.com', '$2a$10$/8XMU9zaTUp3Hbj9II1OxONTx7zvKaWzL7OW30JoikiDkopZqQOGu', 'Leandro Assunção', 88888888888, '2003-04-25', 'PRINCIPAL');

INSERT INTO `group_user_dependent` (`name`, groupType) VALUES
    ('7° ano C', 'CLASS'),
    ('3ª série', 'CLASS'),
    ('1ª série A', 'CLASS'),
    ('Familia recatada', 'FAMILY');

INSERT INTO `dependent` (id, `name`, cpf, registrationNumber) VALUES 
    (3, 'Enzo', '12345678911', '1234567891'),
    (4, 'João', '12345678912', '1234567892'),
    (5, 'Maria', '12345678913', '1234567893');

INSERT INTO `dependent_group` (dependentId, groupId) VALUES
    (3, 3),
    (4, 3),
    (5, 2);

INSERT INTO `user_in_group` (userId, groupId) VALUES
    (1, 3),
    (1, 2),
    (2, 1);

INSERT INTO `relation` (daysOfWeek, userRole, dependentId, userId) VALUES 
    (NULL, 'MOTHER', 4, 1),
    (NULL, 'MOTHER', 3, 1),
    (NULL, 'FATHER', 3, 2),
    (NULL, 'FATHER', 5, 2),
    (NULL, 'FATHER', 3, 1);

INSERT INTO `course` (`name`) VALUES
    ('Português'),
    ('Matemática'),
    ('Ciências'),
    ('História'),
    ('Geografia'),
    ('Inglês');

INSERT INTO `activity` (`name`, dateStart, dateEnd, hourStart, hourEnd,`state`, dependentId, currentUserId, actorId, createdBy, courseId) VALUES 
    ('Atividade no livro pág. 3', '2023-04-21', '2023-04-21', '20:00:00', '21:00:00', 'CREATED', 3, 1, 3, 1, 1),
    ('Apresentação de seminário', '2023-04-21', '2023-04-21', '20:00:00', '21:00:00', 'CREATED', 3, 1, 3, 1, 6),
    ('Atividade no caderno', '2023-05-18', '2023-05-21', '20:00:00', '21:00:00', 'CREATED', 5, 2, 5, 2, 1);

INSERT INTO `spent` (`name`, paidOn, `value`, activityId, dependentId, userId) VALUES 
    ('Aula de campo', '2021-04-26', 100000, 1, 3, 1),
    ('Cantina', '2021-04-27', 100000, NULL, 3, 1);
