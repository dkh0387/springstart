DROP DATABASE IF EXISTS `spring_security_demo_bcrypt`;

CREATE DATABASE IF NOT EXISTS `spring_security_demo_bcrypt`;
USE `spring_security_demo_bcrypt`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`       int(11)     NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL,
    `password` char(68)    NOT NULL,
    `enabled`  tinyint(1)  NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = latin1;

--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
-- NOTE the work of Spring security filters behind the scenes:
-- The plain text pw from user is being encrypted in the same way as the persisted pws
-- The result is being compared to the encrypted pws in the db
-- By match the filter opens, otherwise rejected
--

INSERT INTO `users`
VALUES (1, 'john', '{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 1),
       (2, 'mary', '{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 1),
       (3, 'susan', '{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 1);


--
-- Table structure for table `authorities`
-- NOTE: actually it does not make any sence to provide user id AND user name here,
-- but the standard SQL using by Spring security filter requires: SELECT username, authority FROM authority;
-- by validating passwords
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE authorities
(
    `id`        int(11)     NOT NULL AUTO_INCREMENT,
    `user_id`   int(11)     NOT NULL,
    `username` varchar(50) NOT NULL,
    `authority` varchar(50) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities`
VALUES (1, 1,'john', 'ROLE_EMPLOYEE'),
       (2, 2,'mary', 'ROLE_EMPLOYEE'),
       (3, 2,'mary', 'ROLE_MANAGER'),
       (4, 3, 'susan', 'ROLE_EMPLOYEE'),
       (5, 3,'susan', 'ROLE_ADMIN');