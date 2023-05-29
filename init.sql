DROP TABLE IF EXISTS `price_scale`;
DROP TABLE IF EXISTS `price_list`;
DROP TABLE IF EXISTS `parking_history`;
DROP TABLE IF EXISTS `parking_spot`;
DROP TABLE IF EXISTS `parking_zone`;
DROP TABLE IF EXISTS `parking`;

CREATE TABLE `parking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `parking_zone` (
  `id` int NOT NULL auto_increment,
  `park_id` int NOT NULL,
  `name` varchar(255) default null,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`park_id`),
  KEY `parking_zone_ibfk_1` (`park_id`),
  CONSTRAINT `parking_zone_ibfk_1` FOREIGN KEY (`park_id`) REFERENCES `parking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `parking_spot` (
  `id` int NOT NULL AUTO_INCREMENT,
  `zone_id` int NOT NULL,
  `name` varchar(255) default null,
  `type` varchar(255) DEFAULT NULL,
  `occupied` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`,`zone_id`),
  KEY `parking_spot_ibfk_1` (`zone_id`),
  CONSTRAINT `parking_spot_ibfk_1` FOREIGN KEY (`zone_id`) REFERENCES `parking_zone` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `parking_history` (
  `id` int not null auto_increment,
  `spot_id` int NOT NULL,
  `date_start` datetime NOT NULL,
  `date_end` datetime DEFAULT NULL,
  `total_cost` double DEFAULT NULL,
  `vehicle_plate` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `spot_id` (`spot_id`),
  CONSTRAINT `parking_history_ibfk_1` FOREIGN KEY (`spot_id`) REFERENCES `parking_spot` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `price_list` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_start` datetime DEFAULT NULL,
  `date_end` datetime DEFAULT NULL,
  `type` varchar(55) DEFAULT NULL,
  `zone_id` int NOT NULL,
   CONSTRAINT `parking_zone_id_fk` FOREIGN KEY (`zone_id`) REFERENCES `parking_zone` (`id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `price_scale` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price_list_id` int DEFAULT NULL,
  `duration_of_price_scale` double DEFAULT NULL,
  `scale` double DEFAULT NULL,
  `cost` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `price_list_id` (`price_list_id`),
  CONSTRAINT `price_scale_ibfk_1` FOREIGN KEY (`price_list_id`) REFERENCES `price_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
