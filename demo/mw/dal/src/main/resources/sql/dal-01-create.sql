CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address1` varchar(255) NOT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBB979BF44555C4CE` (`user_id`),
  CONSTRAINT `FKBB979BF44555C4CE` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`user_id`),
  KEY `FK2E3AE94555C4CE` (`user_id`),
  CONSTRAINT `FK2E3AE94555C4CE` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `initial_balance` decimal(19,2) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `type` int NOT NULL,
  `book_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`book_id`),
  KEY `FKB9D38A2D51ED080E` (`book_id`),
  CONSTRAINT `FKB9D38A2D51ED080E` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,0) NOT NULL,
  `memo` varchar(50) DEFAULT NULL,
  `posting_date` date NOT NULL,
  `status` char(1) DEFAULT NULL,
  `from_account_id` bigint(20) NOT NULL,
  `to_account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5C30872C7E16DB` (`from_account_id`),
  KEY `FK5C308726240636A` (`to_account_id`),
  CONSTRAINT `FK5C308726240636A` FOREIGN KEY (`to_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK5C30872C7E16DB` FOREIGN KEY (`from_account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

