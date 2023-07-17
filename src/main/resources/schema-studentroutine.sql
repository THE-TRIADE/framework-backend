CREATE TABLE `user` (
    id BIGINT NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    `password` VARCHAR(256) NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    birthDate DATE NOT NULL,
    `role` VARCHAR(20),
    PRIMARY KEY(id)
);

CREATE TABLE `dependent` (
    id BIGINT NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    cpf CHAR(11) UNIQUE NOT NULL,
    registrationNumber VARCHAR(30) UNIQUE NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE `group_user_dependent` (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL
);

CREATE TABLE `dependent_group` (
    dependentId BIGINT NOT NULL,
    groupId BIGINT NOT NULL,

    FOREIGN KEY (dependentId) REFERENCES `dependent`(id) ON DELETE CASCADE,
    FOREIGN KEY (groupId) REFERENCES `group_user_dependent`(id) ON DELETE CASCADE,
    PRIMARY KEY (groupId, dependentId)
);

CREATE TABLE `user_in_group` (
    userId BIGINT NOT NULL,
    groupId BIGINT NOT NULL,

    FOREIGN KEY (userId) REFERENCES `user`(id) ON DELETE CASCADE,
    FOREIGN KEY (groupId) REFERENCES `group_user_dependent`(id) ON DELETE CASCADE,
    PRIMARY KEY (groupId, userId)
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

    FOREIGN KEY(dependentId) REFERENCES `dependent`(id) ON DELETE CASCADE,
    FOREIGN KEY(userId) REFERENCES `user`(id) ON DELETE CASCADE
);

CREATE TABLE `course` (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL
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
    grade DOUBLE,
    
    dependentId BIGINT NOT NULL,
    currentUserId BIGINT NOT NULL, 
    actorId BIGINT NOT NULL, 
    createdBy BIGINT NOT NULL,
    finishedBy BIGINT,   
    recurringActivityId BIGINT,   
      
    courseId BIGINT NOT NULL,
    FOREIGN KEY(dependentId) REFERENCES `dependent`(id) ON DELETE CASCADE,
    FOREIGN KEY(currentUserId) REFERENCES `user`(id),
    FOREIGN KEY(createdBy) REFERENCES `user`(id),
    FOREIGN KEY(finishedBy) REFERENCES `user`(id) ON DELETE SET NULL,
    FOREIGN KEY(recurringActivityId) REFERENCES `recurring_activity`(id),
    FOREIGN KEY(courseId) REFERENCES `course`(id) ON DELETE SET NULL,
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

    FOREIGN KEY(dependentId) REFERENCES `dependent`(id) ON DELETE CASCADE,
    FOREIGN KEY(userId) REFERENCES `user`(id) ON DELETE CASCADE,
    FOREIGN KEY(activityId) REFERENCES `activity`(id) ON DELETE SET NULL
);