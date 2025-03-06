-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               8.0.41 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for bigfoot
CREATE DATABASE IF NOT EXISTS `bigfoot` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bigfoot`;

-- Dumping structure for table bigfoot.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `username` varchar(30) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `password_UNIQUE` (`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.admin: ~0 rows (approximately)
INSERT INTO `admin` (`username`, `password`) VALUES
	('yaswije', '123');

-- Dumping structure for table bigfoot.brand
CREATE TABLE IF NOT EXISTS `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.brand: ~7 rows (approximately)
INSERT INTO `brand` (`id`, `name`) VALUES
	(1, 'Nike'),
	(2, 'Adidas'),
	(3, 'Puma'),
	(4, 'Gucci'),
	(5, 'Merrel'),
	(6, 'DSI'),
	(7, 'DI');

-- Dumping structure for table bigfoot.cart
CREATE TABLE IF NOT EXISTS `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `qty` varchar(45) NOT NULL,
  `user_email` varchar(45) NOT NULL,
  `stock_no` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cart_user1_idx` (`user_email`),
  KEY `fk_cart_stock1_idx` (`stock_no`),
  CONSTRAINT `fk_cart_stock1` FOREIGN KEY (`stock_no`) REFERENCES `stock` (`stock_no`),
  CONSTRAINT `fk_cart_user1` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.cart: ~1 rows (approximately)
INSERT INTO `cart` (`id`, `qty`, `user_email`, `stock_no`) VALUES
	(25, '1', 'yasojawijerathna@gmail.com', '12_01_01');

-- Dumping structure for table bigfoot.city
CREATE TABLE IF NOT EXISTS `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(45) NOT NULL,
  `district_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_city_district1_idx` (`district_id`),
  CONSTRAINT `fk_city_district1` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.city: ~14 rows (approximately)
INSERT INTO `city` (`id`, `cname`, `district_id`) VALUES
	(1, 'Colombo', 1),
	(2, 'Maradana', 1),
	(3, 'Slave Island', 1),
	(4, 'Nugegoda', 1),
	(5, 'Maharagama', 1),
	(6, 'Gampaha', 2),
	(7, 'Kadawatha', 2),
	(8, 'Kiribathgoda', 2),
	(9, 'Negambo', 2),
	(10, 'Ragama', 2),
	(11, 'Wattala', 2),
	(12, 'Heiyanthuduwa', 2),
	(13, 'Makola', 2),
	(14, 'Delgoda', 2);

-- Dumping structure for table bigfoot.color
CREATE TABLE IF NOT EXISTS `color` (
  `id` int NOT NULL AUTO_INCREMENT,
  `color` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.color: ~11 rows (approximately)
INSERT INTO `color` (`id`, `color`) VALUES
	(1, 'White'),
	(2, 'Black'),
	(3, 'Blue'),
	(4, 'Ash'),
	(5, 'Green'),
	(6, 'Brown'),
	(7, 'Purple'),
	(8, 'Burgundy'),
	(9, 'Pink'),
	(10, 'Green'),
	(11, 'Maroon');

-- Dumping structure for table bigfoot.discount
CREATE TABLE IF NOT EXISTS `discount` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coupon` varchar(45) NOT NULL,
  `percentage` int DEFAULT NULL,
  `value` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.discount: ~0 rows (approximately)
INSERT INTO `discount` (`id`, `coupon`, `percentage`, `value`) VALUES
	(1, '1234', NULL, 500);

-- Dumping structure for table bigfoot.district
CREATE TABLE IF NOT EXISTS `district` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dname` varchar(45) NOT NULL,
  `province_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_district_province1_idx` (`province_id`),
  CONSTRAINT `fk_district_province1` FOREIGN KEY (`province_id`) REFERENCES `province` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.district: ~14 rows (approximately)
INSERT INTO `district` (`id`, `dname`, `province_id`) VALUES
	(1, 'Colombo', 1),
	(2, 'Gampaha', 1),
	(3, 'Kalutara', 1),
	(4, 'Kandy', 2),
	(5, 'Matale', 2),
	(6, 'Badulla', 2),
	(7, 'Galle', 3),
	(8, 'Matara', 3),
	(9, 'Hambanhota', 3),
	(10, 'Jaffna', 4),
	(11, 'Kilinochchi', 4),
	(12, 'Mulawaticu', 4),
	(13, 'Mannar', 4),
	(14, 'Vavuniya', 4);

-- Dumping structure for table bigfoot.gender
CREATE TABLE IF NOT EXISTS `gender` (
  `id` int NOT NULL AUTO_INCREMENT,
  `gender_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.gender: ~2 rows (approximately)
INSERT INTO `gender` (`id`, `gender_name`) VALUES
	(1, 'Male'),
	(2, 'Female');

-- Dumping structure for table bigfoot.image
CREATE TABLE IF NOT EXISTS `image` (
  `code` varchar(150) NOT NULL,
  `product_id` varchar(45) NOT NULL,
  PRIMARY KEY (`code`),
  KEY `fk_image_product1_idx` (`product_id`),
  CONSTRAINT `fk_image_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.image: ~34 rows (approximately)
INSERT INTO `image` (`code`, `product_id`) VALUES
	('resources/men/nike_air1(1).jpg', '1'),
	('resources/men/nike_air1(2).jpg', '1'),
	('resources/men/nike_air1(3).jpg', '1'),
	('resources/women/nike_court(1).jpg', '10'),
	('resources/women/nike_court(2).jpg', '10'),
	('resources/women/nike_court(3).jpg', '10'),
	('resources/women/nike_bella(1).jpg', '11'),
	('resources/women/nike_bella(2).jpg', '11'),
	('resources/women/nike_bella(3).jpg', '11'),
	('resources/women/nike_zoom(1).jpg', '12'),
	('resources/women/nike_zoom(2).jpg', '12'),
	('resources/women/nike_zoom(3).jpg', '12'),
	('resources/men/nike_jordan(1).jpg', '2'),
	('resources/men/nike_jordan(2).jpg', '2'),
	('resources/men/nike_jordan(3).jpg', '2'),
	('resources/men/nike_winflo(1).jpg', '3'),
	('resources/men/nike_jordanS(1).jpg', '4'),
	('resources/men/nike_jordanS(2).jpg', '4'),
	('resources/men/nike_jordanS(3).jpg', '4'),
	('resources/men/adidas_terrex(1).jpg', '5'),
	('resources/men/adidas_terrex(2).jpg', '5'),
	('resources/men/adidas_terrex(3).jpg', '5'),
	('resources/men/adidas_forum(1).jpg', '6'),
	('resources/men/adidas_forum(2).jpg', '6'),
	('resources/men/adidas_forum(3).jpg', '6'),
	('resources/men/adidas_solarglide(1).jpg', '7'),
	('resources/men/adidas_solarglide(2).jpg', '7'),
	('resources/men/adidas_solarglide(3).jpg', '7'),
	('resources/men/adidas_powerlift(1).jpg', '8'),
	('resources/men/adidas_powerlift(2).jpg', '8'),
	('resources/men/adidas_powerlift(3).jpg', '8'),
	('resources/women/nike_airforce1(1).jpg', '9'),
	('resources/women/nike_airforce1(2).jpg', '9'),
	('resources/women/nike_airforce1(3).jpg', '9');

-- Dumping structure for table bigfoot.invoice
CREATE TABLE IF NOT EXISTS `invoice` (
  `id` varchar(45) NOT NULL,
  `date` datetime NOT NULL,
  `total` double NOT NULL,
  `user_email` varchar(45) NOT NULL,
  `discount` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_user1_idx` (`user_email`),
  CONSTRAINT `fk_invoice_user1` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.invoice: ~7 rows (approximately)
INSERT INTO `invoice` (`id`, `date`, `total`, `user_email`, `discount`) VALUES
	('01', '2024-08-06 12:43:07', 7800, 'yasojawijerathna@gmail.com', 0),
	('02', '2024-08-07 11:49:03', 10800, 'yasojawijerathna@gmail.com', 0),
	('03', '2024-08-08 15:41:00', 5600, 'yasojawijerathna@gmail.com', 0),
	('66b5227792421', '2024-08-08 21:54:50', 20500, 'yasojawijerathna@gmail.com', 500),
	('66b6035f12871', '2024-08-09 13:54:35', 5100, 'yasojawijerathna@gmail.com', 0),
	('66b700d4eafa3', '2024-08-10 07:56:13', 5300, 'yasojawijerathna@gmail.com', 0),
	('66b740523934c', '2024-08-10 12:27:11', 14800, 'yasojawijerathna@gmail.com', 500);

-- Dumping structure for table bigfoot.invoice_item
CREATE TABLE IF NOT EXISTS `invoice_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `qty` varchar(45) NOT NULL,
  `stock_no` varchar(45) NOT NULL,
  `invoice_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_item_invoice1_idx` (`invoice_id`),
  KEY `fk_invoice_item_stock` (`stock_no`),
  CONSTRAINT `fk_invoice_item_invoice1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`),
  CONSTRAINT `fk_invoice_item_stock` FOREIGN KEY (`stock_no`) REFERENCES `stock` (`stock_no`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.invoice_item: ~13 rows (approximately)
INSERT INTO `invoice_item` (`id`, `qty`, `stock_no`, `invoice_id`) VALUES
	(1, '2', '08_03_01', '01'),
	(2, '1', '07_04_01', '01'),
	(3, '1', '12_02_01', '02'),
	(4, '2', '11_01_01', '03'),
	(26, '1', '0005_0009_07_31', '66b5227792421'),
	(27, '1', '0009_0003_07_31', '66b5227792421'),
	(28, '1', '11_01_01', '66b5227792421'),
	(29, '1', '0003_0007_07_31', '66b5227792421'),
	(30, '1', '08_04_01', '66b6035f12871'),
	(31, '1', '08_07_01', '66b700d4eafa3'),
	(32, '1', '12_01_01', '66b740523934c'),
	(33, '1', '0009_0003_07_31', '66b740523934c'),
	(34, '1', '0011_0001_08_10', '66b740523934c');

-- Dumping structure for table bigfoot.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` varchar(45) NOT NULL,
  `title` varchar(100) NOT NULL,
  `gender_id` int NOT NULL,
  `brand_id` int NOT NULL,
  `datetime_added` datetime NOT NULL,
  `description` text,
  `caption` text,
  `status_id` int NOT NULL,
  `color_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_gender1_idx` (`gender_id`),
  KEY `fk_product_brand1_idx` (`brand_id`),
  KEY `fk_product_status1_idx` (`status_id`),
  KEY `fk_product_color1_idx` (`color_id`),
  CONSTRAINT `fk_product_brand1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  CONSTRAINT `fk_product_color1` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`),
  CONSTRAINT `fk_product_gender1` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`),
  CONSTRAINT `fk_product_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.product: ~12 rows (approximately)
INSERT INTO `product` (`id`, `title`, `gender_id`, `brand_id`, `datetime_added`, `description`, `caption`, `status_id`, `color_id`) VALUES
	('1', 'Nike-Air Trainer 1 SE', 1, 1, '2022-11-23 15:52:50', NULL, NULL, 2, 1),
	('10', 'Nike-Court Zoom Pro', 2, 1, '2022-12-02 20:53:34', NULL, NULL, 1, 1),
	('11', 'Nike-Air Max Bella ', 2, 1, '2022-12-02 21:02:53', NULL, NULL, 1, 9),
	('12', 'Nike-Zoom Air Fire', 2, 1, '2022-12-02 21:07:47', NULL, NULL, 1, 1),
	('2', 'Nike-Air Jordan 6', 1, 1, '2022-11-23 15:56:34', NULL, NULL, 1, 4),
	('3', 'Nike Winflo 9', 1, 1, '2022-11-23 16:36:54', NULL, NULL, 1, 3),
	('4', 'Nike-Jordan Stay Loyal 2', 1, 1, '2022-11-23 16:44:31', NULL, NULL, 1, 1),
	('5', 'Adidas-Terrex Hiker', 1, 2, '2022-11-23 16:56:32', 'Hike further. Explore more. Enjoy the view. From thru-hikes to weekend explorations, these lightweight hiking shoes offer next-level comfort and support to extend your range in the mountains. A BOOST midsole brings energy to every step to keep you moving on hikes long and short. A GORE-TEX membrane seals out wet conditions while letting your feet breathe as they warm up. The soft, supportive upper and gusseted tongue hug your foot with form-fitting comfort that seals out debris. An internal frame and heel support add the stability to move with confidence over rocky terrain and uneven ground, and the Continental™ Rubber outsole maintains confident traction whether it\'s wet or dry.\r\n\r\n', 'Regular Fit,\r\nLace closure,\r\nMesh upper with seamless overlays,\r\nGORE-TEX membrane,\r\nBOOST midsole,\r\nExternal heel clip and EVA stabilization frame\r\n', 1, 5),
	('6', 'Adidas-FORUM MID SHOES', 1, 2, '2022-12-02 20:08:15', NULL, NULL, 2, 6),
	('7', 'Adidas-Solaglide Shoes', 1, 2, '2022-12-02 20:27:00', NULL, NULL, 1, 2),
	('8', 'Adidas-Powelift 5', 1, 2, '2022-12-02 20:30:28', 'Rubber sole, \r\n\r\nVersatile weightlifting shoes made in part with recycled content, \r\n\r\nDURABLE AND BREATHABLE: Durable canvas upper with mesh inserts for ventilation, \r\n\r\nMIDFOOT STABILITY: Midsole wedge for stability, \r\n\r\nGRIPPY OUTSOLE: The rubber outsole provides outstanding grip and a sleek, low-profile look that keeps your feet planted on the mat, \r\n\r\nMADE WITH RECYCLED CONTENT: Made with a series of recycled materials, this upper features at least 50% recycled content. This product represents just one of our solutions to help end plastic waste.', 'The perfect weightlifting all-rounder, these adidas shoes keep you comfortable with a lightweight and durable canvas upper. The wide instep strap and lacing system combine to ensure midfoot lockdown, while a midsole wedge increases stability. The grippy rubber outsole keeps your stance solid.', 1, 3),
	('9', 'Nike-Air Force 1 LX', 2, 1, '2022-12-02 20:41:58', NULL, NULL, 1, 4);

-- Dumping structure for table bigfoot.profile_image
CREATE TABLE IF NOT EXISTS `profile_image` (
  `path` varchar(100) NOT NULL,
  `user_email` varchar(45) NOT NULL,
  PRIMARY KEY (`path`),
  KEY `fk_profile_image_user1_idx` (`user_email`),
  CONSTRAINT `fk_profile_image_user1` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.profile_image: ~0 rows (approximately)
INSERT INTO `profile_image` (`path`, `user_email`) VALUES
	('resources/avatar/Yasoja_637ddbf6d5cc6.jpeg', 'yasojawijerathna@gmail.com');

-- Dumping structure for table bigfoot.province
CREATE TABLE IF NOT EXISTS `province` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.province: ~9 rows (approximately)
INSERT INTO `province` (`id`, `pname`) VALUES
	(1, 'Western'),
	(2, 'Central'),
	(3, 'Southern'),
	(4, 'Nothern'),
	(5, 'Eastern'),
	(6, 'Sabaragamuwa'),
	(7, 'Wayamba'),
	(8, 'North Central'),
	(9, 'Uwa');

-- Dumping structure for table bigfoot.p_size
CREATE TABLE IF NOT EXISTS `p_size` (
  `id` int NOT NULL AUTO_INCREMENT,
  `size` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.p_size: ~12 rows (approximately)
INSERT INTO `p_size` (`id`, `size`) VALUES
	(1, '7'),
	(2, '7.5'),
	(3, '8'),
	(4, '8.5'),
	(5, '9'),
	(6, '9.5'),
	(7, '10'),
	(8, '10.5'),
	(9, '11'),
	(10, '11.5'),
	(11, '12'),
	(12, '13');

-- Dumping structure for table bigfoot.status
CREATE TABLE IF NOT EXISTS `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.status: ~2 rows (approximately)
INSERT INTO `status` (`id`, `status`) VALUES
	(1, 'Active'),
	(2, 'Deactive');

-- Dumping structure for table bigfoot.stock
CREATE TABLE IF NOT EXISTS `stock` (
  `stock_no` varchar(45) NOT NULL,
  `product_id` varchar(45) NOT NULL,
  `qty` int NOT NULL,
  `stock_price` double NOT NULL,
  `date_added` datetime NOT NULL,
  `status_id` int NOT NULL,
  `p_size_id` int NOT NULL,
  PRIMARY KEY (`stock_no`),
  KEY `fk_stock_status1_idx` (`status_id`),
  KEY `fk_stock_p_size1_idx` (`p_size_id`),
  KEY `fk_stock_product1` (`product_id`),
  CONSTRAINT `fk_stock_p_size1` FOREIGN KEY (`p_size_id`) REFERENCES `p_size` (`id`),
  CONSTRAINT `fk_stock_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_stock_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.stock: ~21 rows (approximately)
INSERT INTO `stock` (`stock_no`, `product_id`, `qty`, `stock_price`, `date_added`, `status_id`, `p_size_id`) VALUES
	('0002_0004_08_10', '2', 12, 5300, '2024-08-10 05:54:50', 1, 4),
	('0002_0009_07_31', '2', 31, 5500, '2024-07-31 16:16:12', 1, 9),
	('0003_0007_07_31', '3', 23, 4800, '2024-07-31 16:10:10', 1, 7),
	('0003_0008_07_31', '3', 28, 4800, '2024-07-31 16:15:03', 1, 8),
	('0004_0007_07_31', '4', 26, 5000, '2024-07-31 16:14:05', 1, 7),
	('0005_0009_07_31', '5', 18, 5200, '2024-07-31 16:20:36', 1, 9),
	('0009_0003_07_31', '9', 16, 4900, '2024-07-31 16:21:12', 1, 3),
	('0010_0001_08_10', '10', 15, 4500, '2024-08-10 11:36:38', 1, 1),
	('0010_0002_08_10', '10', 4500, 5000, '2024-08-10 11:37:12', 1, 2),
	('0011_0001_08_10', '11', 14, 4800, '2024-08-10 05:53:19', 1, 1),
	('07_03_01', '7', 6, 4700, '2024-07-23 22:33:01', 1, 3),
	('07_04_01', '7', 3, 4700, '2024-07-23 22:34:18', 1, 4),
	('07_05_01', '7', 5, 4800, '2024-07-23 22:35:24', 1, 5),
	('07_06_01', '7', 10, 4800, '2024-07-23 22:35:27', 1, 6),
	('08_03_01', '8', 16, 4700, '2024-07-23 22:36:39', 1, 3),
	('08_04_01', '8', 4, 4700, '2024-07-23 22:36:41', 1, 4),
	('08_07_01', '8', 16, 4900, '2024-07-23 22:37:28', 1, 7),
	('11_01_01', '11', 4, 5700, '2024-07-23 22:59:08', 1, 1),
	('11_02_01', '11', 9, 5800, '2024-07-23 22:59:48', 1, 2),
	('12_01_01', '12', 8, 5200, '2024-07-23 23:00:23', 1, 1),
	('12_02_01', '12', 12, 5400, '2024-07-23 23:01:04', 1, 2);

-- Dumping structure for table bigfoot.user
CREATE TABLE IF NOT EXISTS `user` (
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `joined_date` datetime NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `verification_code` varchar(20) DEFAULT NULL,
  `gender_id` int NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`email`),
  KEY `fk_user_gender1_idx` (`gender_id`),
  KEY `fk_user_status1_idx` (`status_id`),
  CONSTRAINT `fk_user_gender1` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`),
  CONSTRAINT `fk_user_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.user: ~5 rows (approximately)
INSERT INTO `user` (`fname`, `lname`, `email`, `password`, `joined_date`, `mobile`, `verification_code`, `gender_id`, `status_id`) VALUES
	('Darshani', 'Nanayakkara', 'dnanayakkara001@gmail.com', 'kandy007', '2022-11-14 23:01:58', '0716548372', NULL, 2, 1),
	('Movindu', 'Senath', 'movindu@gmail.com', 'kandy123', '2024-08-10 10:18:26', '0768945231', '66b6f705aaefb', 1, 1),
	('Oneth', 'Isum', 'oneth@gmail.com', '123465', '2024-08-10 09:44:06', '0784561239', NULL, 1, 2),
	('Subodha', 'Dushshantha', 'subodha@gmail.com', 'Ragama123', '2024-07-15 23:04:16', '0778926314', NULL, 1, 1),
	('Yasoja', 'Wijerathna', 'yasojawijerathna@gmail.com', 'kandy', '2022-07-14 22:57:04', '0705702732', '66b73f648ad8f', 1, 1);

-- Dumping structure for table bigfoot.user_has_address
CREATE TABLE IF NOT EXISTS `user_has_address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `line1` varchar(50) DEFAULT NULL,
  `line2` varchar(50) DEFAULT NULL,
  `postal_code` varchar(5) DEFAULT NULL,
  `user_email` varchar(45) NOT NULL,
  `city_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_has_address_user1_idx` (`user_email`),
  KEY `fk_user_has_address_city1_idx` (`city_id`),
  CONSTRAINT `fk_user_has_address_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `fk_user_has_address_user1` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.user_has_address: ~3 rows (approximately)
INSERT INTO `user_has_address` (`id`, `line1`, `line2`, `postal_code`, `user_email`, `city_id`) VALUES
	(1, '512/1A-20', 'Daluggala Road', '11618', 'yasojawijerathna@gmail.com', 12),
	(7, '370/A', 'Makola South', '11640', 'yasojawijerathna@gmail.com', 13),
	(8, '380/12', 'Makola South', '11828', 'oneth@gmail.com', 2);

-- Dumping structure for table bigfoot.wishlist
CREATE TABLE IF NOT EXISTS `wishlist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_email` varchar(45) NOT NULL,
  `product_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_wishlist_user1_idx` (`user_email`),
  KEY `fk_wishlist_product1_idx1` (`product_id`),
  CONSTRAINT `fk_wishlist_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_wishlist_user1` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigfoot.wishlist: ~0 rows (approximately)
INSERT INTO `wishlist` (`id`, `user_email`, `product_id`) VALUES
	(2, 'yasojawijerathna@gmail.com', '8');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
