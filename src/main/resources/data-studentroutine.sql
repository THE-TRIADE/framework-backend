INSERT INTO `user` (id, email, `password`, `name`, cpf, birthDate, `role`) VALUES 
    (1, 'ana@email.com', '$2a$10$RleiVtfl2ikXq1RYADYTC.rsUoA6eScRlk293ygGXzDjmFWE/yBQq', 'Ana Holanda', 99999999999, '2002-07-13', 'PRINCIPAL'),
    (2, 'leandro@email.com', '$2a$10$/8XMU9zaTUp3Hbj9II1OxONTx7zvKaWzL7OW30JoikiDkopZqQOGu', 'Leandro Assunção', 88888888888, '2003-04-25', 'PRINCIPAL');

INSERT INTO group_user_dependent (`name`) VALUES
    ('7° ano C'),
    ('3ª série'),
    ('1ª série A');

INSERT INTO `dependent` (id, `name`, race, birthDate) VALUES 
    (3, 'Enzo', 'puddle', '2022-07-13'),
    (4, 'João', 'labrador', '2015-04-25'),
    (5, 'Maria', 'cão brabo', '2013-04-25');

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
