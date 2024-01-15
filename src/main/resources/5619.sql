CREATE TABLE `Collect`  (
                            `user_id` int NOT NULL,
                            `product_id` int NOT NULL,
                            PRIMARY KEY (`user_id`, `product_id`)
);

CREATE TABLE `Comment`  (
                            `comment_id` int NOT NULL AUTO_INCREMENT,
                            `product_id` int NOT NULL,
                            `content` varchar(255) NOT NULL,
                            `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`comment_id`)
);

CREATE TABLE `Coupon`  (
                           `coupon_id` int NOT NULL AUTO_INCREMENT,
                           `title` varchar(255) NULL,
                           `code` varchar(255) NULL,
                           `store` varchar(255) NULL,
                           `link` varchar(255) NULL,
                           `product_category` varchar(255) NULL,
                           `start_date` DATE NULL,
                           `end_date` DATE NULL,
                           `description` varchar(255) NULL,
                           `picture` varchar(255) NULL,
                           PRIMARY KEY (`coupon_id`)
);



CREATE TABLE `Products`  (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) NULL,
  `product_name` varchar(255) NULL,
  `category` varchar(255) NULL,
  `orginal_price` decimal(10, 2) NULL,
  `current_price` decimal(10, 2) NULL,
  `picture` varchar(1000) NULL,
  `description` varchar(10000) NULL,
  PRIMARY KEY (`product_id`)
);

CREATE TABLE `User`  (
                         `user_id` int NOT NULL AUTO_INCREMENT,
                         `username` varchar(255) NULL,
                         `password` varchar(255) NULL,
                         `e-mail` varchar(255) NULL,
                         `address` varchar(255) NULL,
                         PRIMARY KEY (`user_id`)
);

ALTER TABLE `Collect` ADD CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `Products` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `Collect` ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `Comment` ADD CONSTRAINT `products_id` FOREIGN KEY (`product_id`) REFERENCES `Products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE;

