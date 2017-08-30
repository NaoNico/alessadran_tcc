
CREATE DATABASE IF NOT EXISTS `alessandratcc`;

USE `alessandratcc`;

CREATE TABLE `Product` (
   `id` int NOT NULL AUTO_INCREMENT,
   `name` varchar(60) NOT NULL,
   `description` varchar(200),
   `price` float NOT NULL,
   `quant` int NOT NULL,
   `datevalidate` DATE NOT NULL,
   `status` varchar(20) NOT NULL,
   `categoria` varchar(20) NOT NULL,
   `menu` int default 0,
   PRIMARY KEY (`id`)
);

CREATE TABLE `User` (
   `id` int NOT NULL AUTO_INCREMENT,
   `username` varchar(60) NOT NULL,
   `password` varchar(300) NOT NULL,
   PRIMARY KEY (`id`)
);

CREATE TABLE `Order` (
   `id` int NOT NULL AUTO_INCREMENT,
   `price` float NOT NULL,
   `user_id` int NOT NULL,
   PRIMARY KEY (`id`),
   FOREIGN KEY (`user_id`) REFERENCES User(`id`)
);

CREATE TABLE `Productorder` (
   `id` int NOT NULL AUTO_INCREMENT,
   `product_id` int NOT NULL,
   `order_id` int NOT NULL,
   `price` float NOT NULL,
   `quant` int NOT NULL,
   PRIMARY KEY (`id`),
   FOREIGN KEY (`product_id`) REFERENCES Product(`id`),
   FOREIGN KEY (`order_id`) REFERENCES Order(`id`)
);

