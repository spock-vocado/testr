
CREATE TABLE `enum_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `enum_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `enum_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `enum_type_id` (`enum_type_id`,`name`),
  KEY `FKBF1B04B33BA60BD1` (`enum_type_id`),
  CONSTRAINT `FKBF1B04B33BA60BD1` FOREIGN KEY (`enum_type_id`) REFERENCES `enum_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

