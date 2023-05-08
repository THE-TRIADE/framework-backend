CREATE TABLE `person` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    birthDate DATE NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE `guardian` (
    email VARCHAR(100) NOT NULL,
    `password` VARCHAR(256) NOT NULL,
    personId BIGINT UNIQUE, 
    FOREIGN KEY(personId) REFERENCES `person`(id) ON DELETE CASCADE,
    PRIMARY KEY(personId)
);

CREATE TABLE `dependent` (
    personId BIGINT UNIQUE,
    FOREIGN KEY(personId) REFERENCES `person`(id) ON DELETE CASCADE,
    PRIMARY KEY(personId)
);

CREATE TABLE `family_group` (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    dependentId BIGINT,
    FOREIGN KEY (dependentId) REFERENCES `dependent`(personId)
);


CREATE TABLE `recurring_activity` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(id)
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
    currentGuardianId BIGINT NOT NULL, 
    actorId BIGINT NOT NULL, 
    createdBy BIGINT NOT NULL,
    finishedBy BIGINT,   
    recurringActivityId BIGINT,   
      
    FOREIGN KEY(dependentId) REFERENCES `dependent`(personId),
    FOREIGN KEY(currentGuardianId) REFERENCES `guardian`(personId),
    FOREIGN KEY(actorId) REFERENCES `person`(id),
    FOREIGN KEY(createdBy) REFERENCES `guardian`(personId),
    FOREIGN KEY(finishedBy) REFERENCES `guardian`(personId),
    FOREIGN KEY(recurringActivityId) REFERENCES `recurring_activity`(id),
    PRIMARY KEY(id)
);

CREATE TABLE `guard`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    daysOfWeek VARCHAR(56),
    guardianRole VARCHAR(9) NOT NULL,

    dependentId BIGINT NOT NULL, 
    guardianId BIGINT NOT NULL, 

    FOREIGN KEY(dependentId) REFERENCES `dependent`(personId) ON DELETE CASCADE,
    FOREIGN KEY(guardianId) REFERENCES `guardian`(personId) ON DELETE CASCADE
);

CREATE TABLE `spent`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    `value` INT NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    paidOn DATE NOT NULL,

    dependentId BIGINT NOT NULL, 
    guardianId BIGINT NOT NULL, 
    activityId BIGINT, 

    FOREIGN KEY(dependentId) REFERENCES `dependent`(personId) ON DELETE CASCADE,
    FOREIGN KEY(guardianId) REFERENCES `guardian`(personId) ON DELETE CASCADE,
    FOREIGN KEY(activityId) REFERENCES `activity`(id)

)