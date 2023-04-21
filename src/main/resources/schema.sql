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

CREATE TABLE `familyGroup` (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    dependentId BIGINT,
    FOREIGN KEY (dependentId) REFERENCES `dependent`(personId)
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
    
    dependentId BIGINT, 
    currentGuardianId BIGINT, 
    actorId BIGINT, 
    createdBy BIGINT,   
      
    FOREIGN KEY(dependentId) REFERENCES `dependent`(personId),
    FOREIGN KEY(currentGuardianId) REFERENCES `guardian`(personId),
    FOREIGN KEY(actorId) REFERENCES `person`(id),
    FOREIGN KEY(createdBy) REFERENCES `guardian`(personId),
    PRIMARY KEY(id)
);
