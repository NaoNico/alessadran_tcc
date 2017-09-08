
CREATE DATABASE IF NOT EXISTS `alessandratcc`;

USE `alessandratcc`;

CREATE TABLE `product` (
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

CREATE TABLE `user` (
   `id` int NOT NULL AUTO_INCREMENT,
   `username` varchar(60) NOT NULL,
   `password` varchar(300) NOT NULL,
   PRIMARY KEY (`id`)
);

CREATE TABLE `orders` (
   `id` int NOT NULL AUTO_INCREMENT,
   `price` float NOT NULL,
   `user_id` int NOT NULL,
   `date` DATE NOT NULL,
   `status` varchar(20) NOT NULL,
   PRIMARY KEY (`id`),
   FOREIGN KEY (`user_id`) REFERENCES user(`id`)
);

CREATE TABLE `productorder` (
   `id` int NOT NULL AUTO_INCREMENT,
   `product_id` int NOT NULL,
   `order_id` int NOT NULL,
   `price` float NOT NULL,
   `quant` int NOT NULL,
   `name` varchar(60) NOT NULL,
   PRIMARY KEY (`id`),
   FOREIGN KEY (`product_id`) REFERENCES product(`id`)
);

