INSERT INTO `user` (id, email, `password`, `name`, cpf, birthDate, `role`) VALUES 
    (1, 'ana@email.com', '$2a$10$RleiVtfl2ikXq1RYADYTC.rsUoA6eScRlk293ygGXzDjmFWE/yBQq', 'Ana Holanda', '99999999999', '2002-07-13', 'PRINCIPAL'),
    (2, 'leandro@email.com', '$2a$10$/8XMU9zaTUp3Hbj9II1OxONTx7zvKaWzL7OW30JoikiDkopZqQOGu', 'Leandro Assunção', '88888888888', '2003-04-25', 'PRINCIPAL'),
    (3, 'fe.silva@email.com', '$2a$10$RleiVtfl2ikXq1RYADYTC.rsUoA6eScRlk293ygGXzDjmFWE/yBQq', 'Fernanda Silva', '88888888888', '1985-07-10', 'TEACHER'),
    (4, 'oliver.r@email.com', '$2a$10$RleiVtfl2ikXq1RYADYTC.rsUoA6eScRlk293ygGXzDjmFWE/yBQq', 'Rodrigo Oliveira', '77777777777', '1992-09-15', 'TEACHER'),
    (5, 'amancost@email.com', '$2a$10$RleiVtfl2ikXq1RYADYTC.rsUoA6eScRlk293ygGXzDjmFWE/yBQq', 'Amanda Costa', '66666666666', '1988-03-22', 'TEACHER'),
    (6, 'ricardo.sts@email.com', '$2a$10$RleiVtfl2ikXq1RYADYTC.rsUoA6eScRlk293ygGXzDjmFWE/yBQq', 'Ricardo Santos', '55555555555', '1995-05-27', 'RELATIVE'),
    (7, 'julianamartins@email.com', '$2a$10$RleiVtfl2ikXq1RYADYTC.rsUoA6eScRlk293ygGXzDjmFWE/yBQq', 'Juliana Martins', '44444444444', '1990-11-12', 'RELATIVE');

INSERT INTO `group_user_dependent` (`name`, groupType) VALUES
    ('7° ano C', 'CLASS'),
    ('3ª série', 'CLASS'),
    ('1ª série A', 'CLASS'),
    ('Familia Santos', 'FAMILY'),
    ('Familia Martins', 'FAMILY');

INSERT INTO `dependent` (id, `name`, cpf, registrationNumber) VALUES 
    (8, 'Enzo', '12345678911', '1234567891'),
    (9, 'João', '12345678912', '1234567892'),
    (10, 'Maria', '12345678913', '1234567893'),
    (11, 'Tiago', '12345678924', '0989898998'),
    (12, 'Laura', '12345678914', '1234567894'),
    (13, 'Pedro', '12345678915', '1234567895'),
    (14, 'Ana', '12345678916', '1234567896'),
    (15, 'Luiza', '12345678917', '1234567897'),
    (16, 'Gabriel', '12345678918', '1234567898'),
    (17, 'Sofia', '12345678919', '1234567899'),
    (18, 'Rafael', '12345678920', '1234567800'),
    (19, 'Isabela', '12345678921', '1234567801'),
    (20, 'Eduardo', '12345678922', '1234567802'),
    (21, 'Lara', '12345678923', '1234567803');

INSERT INTO `dependent_group` (dependentId, groupId) VALUES
    (8, 1),
    (9, 1),
    (10, 1),
    (11, 1),
    (12, 2),
    (13, 2),
    (14, 2),
    (15, 2),
    (16, 2),
    (17, 3),
    (18, 3),
    (19, 3),
    (20, 3),
    (21, 3),
    (8, 4),
    (9, 4),
    (10, 4),
    (11, 4),
    (12, 4),
    (13, 4),
    (14, 4),
    (15, 5),
    (16, 5),
    (17, 5),
    (18, 5),
    (19, 5),
    (20, 5),
    (21, 5);

INSERT INTO `user_in_group` (userId, groupId) VALUES
    (6, 4),
    (7, 5),
    (3, 1),
    (4, 2),
    (5, 3);

INSERT INTO `relation` (daysOfWeek, userRole, dependentId, userId) VALUES 
    (NULL, 'MOTHER', 15, 7),
    (NULL, 'MOTHER', 16, 7),
    (NULL, 'MOTHER', 17, 7),
    (NULL, 'MOTHER', 18, 7),
    (NULL, 'MOTHER', 19, 7),
    (NULL, 'MOTHER', 20, 7),
    (NULL, 'MOTHER', 21, 7),
    (NULL, 'FATHER', 8, 6),
    (NULL, 'FATHER', 9, 6),
    (NULL, 'FATHER', 10, 6),
    (NULL, 'FATHER', 11, 6),
    (NULL, 'FATHER', 12, 6),
    (NULL, 'FATHER', 13, 6),
    (NULL, 'FATHER', 14, 6);

INSERT INTO `course` (`name`) VALUES
    ('Português'),
    ('Matemática'),
    ('Ciências'),
    ('História'),
    ('Geografia'),
    ('Inglês');

INSERT INTO `activity` (`name`, dateStart, dateEnd, hourStart, hourEnd,`state`, dependentId, currentUserId, actorId, createdBy, courseId) VALUES 
    ('Atividade no livro pág. 3', '2023-04-21', '2023-04-21', '20:00:00', '21:00:00', 'CREATED', 8, 6, 8, 1, 1),
    ('Apresentação de seminário', '2023-04-21', '2023-04-21', '20:00:00', '21:00:00', 'CREATED', 17, 7, 17, 1, 6),
    ('Atividade no caderno', '2023-05-18', '2023-05-21', '20:00:00', '21:00:00', 'CREATED', 21, 7, 21, 2, 1);

INSERT INTO `spent` (`name`, paidOn, `value`, activityId, dependentId, userId) VALUES 
    ('Aula de campo', '2021-04-26', 100000, 1, 8, 6),
    ('Cantina', '2021-04-27', 100000, NULL, 13, 6);
