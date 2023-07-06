CREATE TABLE `person` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    birthDate DATE NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE `user` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    `password` VARCHAR(256) NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    birthDate DATE NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE `group_user_dependent` (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL
);

CREATE TABLE `dependent` (
    personId BIGINT UNIQUE,

    groupId BIGINT,
    FOREIGN KEY (groupId) REFERENCES `group_user_dependent`(id),

    FOREIGN KEY(personId) REFERENCES `person`(id) ON DELETE CASCADE,
    PRIMARY KEY(personId)

);

CREATE TABLE `recurring_activity` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(id)
);

CREATE TABLE `relation`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    daysOfWeek VARCHAR(56),
    userRole VARCHAR(9) NOT NULL,

    dependentId BIGINT NOT NULL, 
    userId BIGINT NOT NULL,

    FOREIGN KEY(dependentId) REFERENCES `dependent`(personId) ON DELETE CASCADE,
    FOREIGN KEY(userId) REFERENCES `user`(id) ON DELETE CASCADE
);

CREATE TABLE `activity`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `description` VARCHAR(500),
    dateStart DATE NOT NULL,
    dateEnd DATE NOT NULL,
    hourStart TIME NOT NULL,
    hourEnd TIME NOT NULL,
    `state` VARCHAR(50) NOT NULL,
    commentary VARCHAR(500),
    
    dependentId BIGINT NOT NULL,
    currentUserId BIGINT NOT NULL, 
    actorId BIGINT NOT NULL, 
    createdBy BIGINT NOT NULL,
    finishedBy BIGINT,   
    recurringActivityId BIGINT,   
      
    FOREIGN KEY(dependentId) REFERENCES `dependent`(personId),
    FOREIGN KEY(currentUserId) REFERENCES `user`(id),
    FOREIGN KEY(actorId) REFERENCES `person`(id),
    FOREIGN KEY(createdBy) REFERENCES `user`(id),
    FOREIGN KEY(finishedBy) REFERENCES `user`(id),
    FOREIGN KEY(recurringActivityId) REFERENCES `recurring_activity`(id),
    PRIMARY KEY(id)
);

CREATE TABLE `spent`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    `value` INT NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    paidOn DATE NOT NULL,

    dependentId BIGINT NOT NULL, 
    userId BIGINT NOT NULL, 
    activityId BIGINT, 

    FOREIGN KEY(dependentId) REFERENCES `dependent`(personId) ON DELETE CASCADE,
    FOREIGN KEY(userId) REFERENCES `user`(id) ON DELETE CASCADE,
    FOREIGN KEY(activityId) REFERENCES `activity`(id)
);