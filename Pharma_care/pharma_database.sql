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


-- Dumping database structure for pharma_care
CREATE DATABASE IF NOT EXISTS `pharma_care` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pharma_care`;

-- Dumping structure for table pharma_care.brand
CREATE TABLE IF NOT EXISTS `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.brand: ~10 rows (approximately)
INSERT INTO `brand` (`id`, `name`) VALUES
	(1, 'Panadol'),
	(2, 'Amoxil'),
	(3, 'Benadryl'),
	(4, 'Gaviscon'),
	(5, 'Aspirin Cardio'),
	(6, 'Glyciphage'),
	(7, 'Ventolin'),
	(8, 'Celin'),
	(9, 'Clarityn'),
	(10, 'Sinddhalepa');

-- Dumping structure for table pharma_care.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.category: ~9 rows (approximately)
INSERT INTO `category` (`id`, `name`) VALUES
	(1, 'Pain Relief'),
	(2, 'Antibiotics'),
	(3, 'Cough and Cold'),
	(4, 'Digestive Health'),
	(5, 'Cardiovascular'),
	(6, 'Diabetes Management'),
	(7, 'Respiratory'),
	(8, 'Vitamins and Supplements'),
	(9, 'Allergy');

-- Dumping structure for table pharma_care.company
CREATE TABLE IF NOT EXISTS `company` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.company: ~10 rows (approximately)
INSERT INTO `company` (`id`, `name`, `mobile`) VALUES
	(1, 'State Pharmaceuticals Corporation (SPC)', '0771234567'),
	(2, 'Hemas Pharmaceuticals', '0772345678'),
	(3, 'Navesta Pharmaceuticals', '0773456789'),
	(4, 'Astron Limited', '0774567890'),
	(5, 'Cipla Sri Lanka', '0775678901'),
	(6, 'GlaxoSmithKline (GSK) Sri Lanka', '0776789012'),
	(7, 'Piramal Healthcare Sri Lanka', '0777890123'),
	(8, 'Sun Pharmaceutical Sri Lanka', '0778901234'),
	(9, 'Ceylon Pharmaceuticals', '0779012345'),
	(10, 'Swiss Biogenics Limited', '0770123456');

-- Dumping structure for table pharma_care.goods_receive
CREATE TABLE IF NOT EXISTS `goods_receive` (
  `id` varchar(25) NOT NULL,
  `date` date NOT NULL,
  `total` varchar(45) NOT NULL,
  `payment_method_id` int NOT NULL,
  `user_id` varchar(10) NOT NULL,
  `supplier_id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_goods_receive_payment_method1_idx` (`payment_method_id`),
  KEY `fk_goods_receive_user1_idx` (`user_id`),
  KEY `fk_goods_receive_supplier1_idx` (`supplier_id`),
  CONSTRAINT `fk_goods_receive_payment_method1` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`),
  CONSTRAINT `fk_goods_receive_supplier1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `fk_goods_receive_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.goods_receive: ~15 rows (approximately)
INSERT INTO `goods_receive` (`id`, `date`, `total`, `payment_method_id`, `user_id`, `supplier_id`) VALUES
	('GRN_0001', '2024-08-30', '6900', 1, 'U_0001', 'SP_002'),
	('GRN_0002', '2024-08-30', '10350', 1, 'U_0001', 'SP_001'),
	('GRN_0003', '2024-08-30', '11700', 1, 'U_0001', 'SP_001'),
	('GRN_0004', '2024-08-30', '2250', 2, 'U_0001', 'SP_002'),
	('GRN_0005', '2024-08-30', '28800', 2, 'U_0001', 'SP_002'),
	('GRN_0006', '2024-08-30', '1350', 2, 'U_0001', 'SP_002'),
	('GRN_0007', '2024-08-30', '18200', 1, 'U_0001', 'SP_002'),
	('GRN_0008', '2024-08-30', '18200', 1, 'U_0001', 'SP_002'),
	('GRN_0009', '2024-08-30', '3900', 2, 'U_0005', 'SP_002'),
	('GRN_0010', '2024-08-30', '3900', 2, 'U_0005', 'SP_002'),
	('GRN_0011', '2024-08-30', '37500', 1, 'U_0005', 'SP_002'),
	('GRN_0012', '2024-08-30', '23000', 2, 'U_0005', 'SP_001'),
	('GRN_0013', '2024-08-30', '1500', 2, 'U_0005', 'SP_001'),
	('GRN_0014', '2024-08-31', '6500', 2, 'U_0005', 'SP_002'),
	('GRN_0015', '2024-09-04', '8400', 1, 'U_0001', 'SP_002');

-- Dumping structure for table pharma_care.goods_receive_item
CREATE TABLE IF NOT EXISTS `goods_receive_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `qty` int NOT NULL,
  `grn_id` varchar(25) NOT NULL,
  `barcode` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_goods_receive_item_goods_receive1_idx` (`grn_id`),
  KEY `fk_goods_receive_item_stock1_idx` (`barcode`),
  CONSTRAINT `fk_goods_receive_item_goods_receive1` FOREIGN KEY (`grn_id`) REFERENCES `goods_receive` (`id`),
  CONSTRAINT `fk_goods_receive_item_stock1` FOREIGN KEY (`barcode`) REFERENCES `stock` (`barcode`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.goods_receive_item: ~15 rows (approximately)
INSERT INTO `goods_receive_item` (`id`, `qty`, `grn_id`, `barcode`) VALUES
	(5, 15, 'GRN_0001', 'P_003-002-001'),
	(6, 12, 'GRN_0002', 'P_005-003-001'),
	(7, 15, 'GRN_0003', 'P_005-003-002'),
	(8, 15, 'GRN_0004', 'P_009-005-001'),
	(9, 15, 'GRN_0005', 'P_008-005-001'),
	(10, 15, 'GRN_0006', 'P_011-007-001'),
	(11, 13, 'GRN_0007', 'P_002-002-001'),
	(12, 11, 'GRN_0008', 'P_002-002-001'),
	(13, 16, 'GRN_0009', 'P_002-002-002'),
	(14, 15, 'GRN_0010', 'P_002-002-002'),
	(15, 8, 'GRN_0011', 'P_015-009-001'),
	(16, 8, 'GRN_0012', 'P_013-008-001'),
	(17, 8, 'GRN_0013', 'P_014-008-001'),
	(18, 8, 'GRN_0014', 'P_002-002-003'),
	(19, 15, 'GRN_0015', 'P_003-002-002');

-- Dumping structure for table pharma_care.goods_return
CREATE TABLE IF NOT EXISTS `goods_return` (
  `id` varchar(25) NOT NULL,
  `date` date NOT NULL,
  `total` double NOT NULL,
  `status_id` int NOT NULL,
  `company_id` int NOT NULL,
  `user_id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_goods_return_status1_idx` (`status_id`),
  KEY `fk_goods_return_company1_idx` (`company_id`),
  KEY `fk_goods_return_user1_idx` (`user_id`),
  CONSTRAINT `fk_goods_return_company1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `fk_goods_return_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_goods_return_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.goods_return: ~9 rows (approximately)
INSERT INTO `goods_return` (`id`, `date`, `total`, `status_id`, `company_id`, `user_id`) VALUES
	('GRetN_0001', '2024-08-30', 4500, 1, 6, 'U_0001'),
	('GRetN_0002', '2024-08-30', 2300, 1, 5, 'U_0001'),
	('GRetN_0003', '2024-08-31', 9200, 1, 5, 'U_0001'),
	('GRetN_0004', '2024-08-31', 90, 1, 2, 'U_0001'),
	('GRetN_0005', '2024-08-31', 460, 1, 1, 'U_0001'),
	('GRetN_0006', '2024-08-31', 230, 1, 1, 'U_0001'),
	('GRetN_0007', '2024-08-31', 90, 1, 2, 'U_0001'),
	('GRetN_0008', '2024-09-01', 790, 1, 1, 'U_0001'),
	('GRetN_0009', '2024-09-01', 2750, 1, 2, 'U_0001');

-- Dumping structure for table pharma_care.goods_return_item
CREATE TABLE IF NOT EXISTS `goods_return_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `qty` int NOT NULL,
  `barcode` varchar(30) NOT NULL,
  `grtn_id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_goods_return_item_stock1_idx` (`barcode`),
  KEY `fk_goods_return_item_goods_return1_idx` (`grtn_id`),
  CONSTRAINT `fk_goods_return_item_goods_return1` FOREIGN KEY (`grtn_id`) REFERENCES `goods_return` (`id`),
  CONSTRAINT `fk_goods_return_item_stock1` FOREIGN KEY (`barcode`) REFERENCES `stock` (`barcode`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.goods_return_item: ~11 rows (approximately)
INSERT INTO `goods_return_item` (`id`, `qty`, `barcode`, `grtn_id`) VALUES
	(2, 10, 'P_001-001-001', 'GRetN_0001'),
	(3, 10, 'P_003-002-001', 'GRetN_0002'),
	(4, 110, 'P_003-002-001', 'GRetN_0003'),
	(8, 2, 'P_009-005-001', 'GRetN_0004'),
	(9, 2, 'P_005-003-001', 'GRetN_0005'),
	(10, 1, 'P_005-003-001', 'GRetN_0006'),
	(11, 2, 'P_009-005-001', 'GRetN_0007'),
	(12, 6, 'P_009-005-001', 'GRetN_0008'),
	(13, 2, 'P_005-003-002', 'GRetN_0008'),
	(14, 10, 'P_003-002-001', 'GRetN_0009'),
	(15, 5, 'P_011-007-001', 'GRetN_0009');

-- Dumping structure for table pharma_care.invoice
CREATE TABLE IF NOT EXISTS `invoice` (
  `id` varchar(25) NOT NULL,
  `issue_date` date NOT NULL,
  `total` double NOT NULL,
  `payment_method_id` int NOT NULL,
  `user_id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_payment_method1_idx` (`payment_method_id`),
  KEY `fk_invoice_user1_idx` (`user_id`),
  CONSTRAINT `fk_invoice_payment_method1` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`),
  CONSTRAINT `fk_invoice_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.invoice: ~36 rows (approximately)
INSERT INTO `invoice` (`id`, `issue_date`, `total`, `payment_method_id`, `user_id`) VALUES
	('IN_00001', '2024-08-30', 1110, 1, 'U_0005'),
	('IN_00002', '2024-08-30', 2800, 1, 'U_0005'),
	('IN_00003', '2024-08-30', 1100, 1, 'U_0005'),
	('IN_00004', '2024-08-30', 1650, 1, 'U_0005'),
	('IN_00005', '2024-08-30', 1100, 1, 'U_0005'),
	('IN_00006', '2024-08-30', 1650, 1, 'U_0005'),
	('IN_00007', '2024-08-30', 1650, 1, 'U_0005'),
	('IN_00008', '2024-08-30', 550, 1, 'U_0005'),
	('IN_00009', '2024-08-30', 1120, 1, 'U_0005'),
	('IN_00010', '2024-08-30', 1120, 1, 'U_0005'),
	('IN_00011', '2024-08-30', 1640, 3, 'U_0005'),
	('IN_00012', '2024-08-30', 870, 1, 'U_0005'),
	('IN_00013', '2024-08-30', 520, 1, 'U_0005'),
	('IN_00014', '2024-08-30', 520, 1, 'U_0005'),
	('IN_00015', '2024-08-30', 520, 1, 'U_0005'),
	('IN_00016', '2024-08-30', 520, 1, 'U_0005'),
	('IN_00017', '2024-08-30', 260, 3, 'U_0005'),
	('IN_00018', '2024-08-30', 260, 1, 'U_0005'),
	('IN_00019', '2024-08-30', 1600, 1, 'U_0005'),
	('IN_00020', '2024-08-30', 1600, 1, 'U_0005'),
	('IN_00021', '2024-08-30', 290, 1, 'U_0005'),
	('IN_00022', '2024-08-30', 290, 1, 'U_0005'),
	('IN_00023', '2024-08-30', 290, 3, 'U_0005'),
	('IN_00024', '2024-08-30', 260, 1, 'U_0005'),
	('IN_00025', '2024-08-30', 290, 3, 'U_0005'),
	('IN_00026', '2024-08-30', 560, 3, 'U_0005'),
	('IN_00027', '2024-08-30', 260, 1, 'U_0005'),
	('IN_00028', '2024-08-30', 560, 1, 'U_0005'),
	('IN_00029', '2024-08-30', 290, 1, 'U_0005'),
	('IN_00030', '2024-08-30', 560, 1, 'U_0005'),
	('IN_00031', '2024-08-30', 560, 1, 'U_0005'),
	('IN_00032', '2024-08-31', 290, 1, 'U_0005'),
	('IN_00033', '2024-08-31', 4060, 1, 'U_0005'),
	('IN_00034', '2024-08-31', 290, 3, 'U_0005'),
	('IN_00035', '2024-08-31', 1680, 1, 'U_0005'),
	('IN_00036', '2024-09-01', 780, 3, 'U_0005');

-- Dumping structure for table pharma_care.invoice_item
CREATE TABLE IF NOT EXISTS `invoice_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `qty` int NOT NULL,
  `barcode` varchar(30) NOT NULL,
  `invoice_id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_item_stock1_idx` (`barcode`),
  KEY `fk_invoice_item_invoice1_idx` (`invoice_id`),
  CONSTRAINT `fk_invoice_item_invoice1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`),
  CONSTRAINT `fk_invoice_item_stock1` FOREIGN KEY (`barcode`) REFERENCES `stock` (`barcode`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.invoice_item: ~37 rows (approximately)
INSERT INTO `invoice_item` (`id`, `qty`, `barcode`, `invoice_id`) VALUES
	(1, 2, 'P_001-001-001', 'IN_00001'),
	(2, 5, 'P_001-001-002', 'IN_00002'),
	(3, 2, 'P_001-001-001', 'IN_00003'),
	(4, 3, 'P_001-001-001', 'IN_00004'),
	(5, 2, 'P_001-001-001', 'IN_00005'),
	(6, 3, 'P_001-001-001', 'IN_00006'),
	(7, 3, 'P_001-001-001', 'IN_00007'),
	(8, 1, 'P_001-001-001', 'IN_00008'),
	(9, 2, 'P_001-001-002', 'IN_00009'),
	(10, 2, 'P_001-001-002', 'IN_00010'),
	(11, 2, 'P_005-003-001', 'IN_00011'),
	(12, 2, 'P_001-001-002', 'IN_00011'),
	(13, 3, 'P_005-003-002', 'IN_00012'),
	(14, 2, 'P_005-003-001', 'IN_00013'),
	(15, 2, 'P_005-003-001', 'IN_00014'),
	(16, 2, 'P_005-003-001', 'IN_00015'),
	(17, 2, 'P_005-003-001', 'IN_00016'),
	(18, 1, 'P_005-003-001', 'IN_00017'),
	(19, 1, 'P_005-003-001', 'IN_00018'),
	(20, 1, 'P_015-009-001', 'IN_00019'),
	(21, 1, 'P_015-009-001', 'IN_00020'),
	(22, 1, 'P_005-003-002', 'IN_00021'),
	(23, 1, 'P_005-003-002', 'IN_00022'),
	(24, 1, 'P_005-003-002', 'IN_00023'),
	(25, 1, 'P_005-003-001', 'IN_00024'),
	(26, 1, 'P_002-002-002', 'IN_00025'),
	(27, 1, 'P_002-002-001', 'IN_00026'),
	(28, 1, 'P_003-002-001', 'IN_00027'),
	(29, 1, 'P_002-002-001', 'IN_00028'),
	(30, 1, 'P_005-003-002', 'IN_00029'),
	(31, 1, 'P_001-001-002', 'IN_00030'),
	(32, 1, 'P_001-001-002', 'IN_00031'),
	(33, 1, 'P_005-003-002', 'IN_00032'),
	(34, 14, 'P_002-002-002', 'IN_00033'),
	(35, 1, 'P_005-003-002', 'IN_00034'),
	(36, 3, 'P_002-002-001', 'IN_00035'),
	(37, 3, 'P_005-003-001', 'IN_00036');

-- Dumping structure for table pharma_care.login
CREATE TABLE IF NOT EXISTS `login` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `user_id` varchar(10) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `logincol_UNIQUE` (`password`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  CONSTRAINT `fk_login_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.login: ~15 rows (approximately)
INSERT INTO `login` (`username`, `password`, `user_id`) VALUES
	('yas', 'Matale@007', 'U_0001'),
	('123', 'Colombo@123', 'U_0005'),
	('robert.brown', 'RobertBrown@123', 'U_0006'),
	('emily.davis', 'EmilyDavis@123', 'U_0007'),
	('michael.johnson', 'MichaelJohnson@123', 'U_0008'),
	('patricia.wilson', 'PatriciaWilson@123', 'U_0009'),
	('linda.martinez', 'LindaMartinez@123', 'U_0010'),
	('james.clark', 'JamesClark@123', 'U_0011'),
	('barbara.lewis', 'BarbaraLewis@123', 'U_0012'),
	('william.lee', 'WilliamLee@123', 'U_0013'),
	('akila.fernando', 'Akila@415', 'U_0014'),
	('susan.miller', 'SusanMiller@123', 'U_0015'),
	('malith', 'malith@2DE', 'U_0016'),
	('sumithra12', 'Kji12@@1', 'U_0017'),
	('thara', 'asdD78@F', 'U_0020');

-- Dumping structure for table pharma_care.mobile
CREATE TABLE IF NOT EXISTS `mobile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mobile` varchar(20) NOT NULL,
  `user_id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mobile_user1_idx` (`user_id`),
  CONSTRAINT `fk_mobile_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.mobile: ~28 rows (approximately)
INSERT INTO `mobile` (`id`, `mobile`, `user_id`) VALUES
	(1, '0705702732', 'U_0001'),
	(2, '0771234567', 'U_0003'),
	(3, '0772345678', 'U_0004'),
	(7, '0752613489', 'U_0005'),
	(8, '0748521459', 'U_0018'),
	(9, '0715256522', 'U_0005'),
	(10, '0715256523', 'U_0005'),
	(11, '0715256524', 'U_0006'),
	(12, '0715256525', 'U_0006'),
	(13, '0715256526', 'U_0007'),
	(14, '0715256527', 'U_0007'),
	(15, '0715256528', 'U_0008'),
	(16, '0715256529', 'U_0008'),
	(17, '0715256530', 'U_0009'),
	(18, '0715256531', 'U_0009'),
	(19, '0715256532', 'U_0010'),
	(20, '0715256533', 'U_0010'),
	(21, '0715256534', 'U_0011'),
	(22, '0715256535', 'U_0011'),
	(23, '0715256536', 'U_0012'),
	(24, '0715256537', 'U_0012'),
	(25, '0715256538', 'U_0013'),
	(26, '0715256539', 'U_0013'),
	(27, '0715256540', 'U_0014'),
	(28, '0715256541', 'U_0014'),
	(29, '0714523652', 'U_0016'),
	(30, '0712583694', 'U_0019'),
	(31, '0713215751', 'U_0020');

-- Dumping structure for table pharma_care.payment_method
CREATE TABLE IF NOT EXISTS `payment_method` (
  `id` int NOT NULL AUTO_INCREMENT,
  `method` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.payment_method: ~3 rows (approximately)
INSERT INTO `payment_method` (`id`, `method`) VALUES
	(1, 'Cash'),
	(2, 'Credit Line'),
	(3, 'Card');

-- Dumping structure for table pharma_care.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` varchar(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  `category_id` int NOT NULL,
  `brand_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_category1_idx` (`category_id`),
  KEY `fk_product_Brand1_idx` (`brand_id`),
  CONSTRAINT `fk_product_Brand1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  CONSTRAINT `fk_product_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.product: ~16 rows (approximately)
INSERT INTO `product` (`id`, `name`, `category_id`, `brand_id`) VALUES
	('P_001', 'Paracetamol', 1, 1),
	('P_002', 'Propyphenazone + Caffeine', 1, 2),
	('P_003', 'Amoxicillin', 2, 2),
	('P_004', 'Ciprofloxacin', 2, 3),
	('P_005', 'Diphenhydramine', 3, 3),
	('P_006', 'Dextromethorphan + Chlorpheniramine', 3, 4),
	('P_007', 'Sodium Alginate', 4, 4),
	('P_008', 'Sodium Bicarbonate', 4, 5),
	('P_009', 'Low-dose Aspirin', 5, 5),
	('P_010', 'Atorvastatin', 5, 6),
	('P_011', 'Metformin', 6, 7),
	('P_012', 'Sitagliptin', 6, 7),
	('P_013', 'Salbutamol', 7, 8),
	('P_014', 'Vitamin C', 8, 8),
	('P_015', 'Loratadine', 9, 9),
	('P_016', 'Penicilin', 2, 4);

-- Dumping structure for table pharma_care.status
CREATE TABLE IF NOT EXISTS `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.status: ~2 rows (approximately)
INSERT INTO `status` (`id`, `name`) VALUES
	(1, 'ACTIVE'),
	(2, 'INACTIVE');

-- Dumping structure for table pharma_care.stock
CREATE TABLE IF NOT EXISTS `stock` (
  `barcode` varchar(30) NOT NULL,
  `qty` int NOT NULL,
  `buying_price` double NOT NULL,
  `selling_price` double NOT NULL,
  `mfd` date NOT NULL,
  `exp` date NOT NULL,
  `status_id` int NOT NULL,
  `product_id` varchar(20) NOT NULL,
  PRIMARY KEY (`barcode`),
  KEY `fk_stock_status1_idx` (`status_id`),
  KEY `fk_stock_product1_idx` (`product_id`),
  CONSTRAINT `fk_stock_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_stock_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.stock: ~15 rows (approximately)
INSERT INTO `stock` (`barcode`, `qty`, `buying_price`, `selling_price`, `mfd`, `exp`, `status_id`, `product_id`) VALUES
	('P_001-001-001', 16, 450, 550, '2024-03-29', '2024-12-29', 1, 'P_001'),
	('P_001-001-002', 27, 460, 560, '2024-06-29', '2026-02-27', 1, 'P_001'),
	('P_002-002-001', 28, 520, 560, '2024-08-01', '2024-07-31', 2, 'P_002'),
	('P_002-002-002', 2, 260, 290, '2024-08-02', '2024-08-15', 2, 'P_002'),
	('P_002-002-003', 25, 260, 290, '2024-05-07', '2024-08-12', 2, 'P_002'),
	('P_003-002-001', 19, 230, 260, '2024-06-04', '2024-09-15', 1, 'P_003'),
	('P_003-002-002', 15, 560, 600, '2024-09-02', '2024-09-14', 1, 'P_003'),
	('P_005-003-001', 28, 230, 260, '2024-04-04', '2028-08-25', 1, 'P_005'),
	('P_005-003-002', 29, 260, 290, '2024-05-01', '2025-08-08', 1, 'P_005'),
	('P_008-005-001', 30, 960, 1000, '2024-08-01', '2024-09-22', 1, 'P_008'),
	('P_009-005-001', 40, 45, 55, '2024-07-03', '2025-01-03', 1, 'P_009'),
	('P_011-007-001', 10, 90, 110, '2024-08-13', '2024-09-25', 1, 'P_011'),
	('P_013-008-001', 50, 460, 500, '2024-05-15', '2024-12-19', 1, 'P_013'),
	('P_014-008-001', 50, 30, 45, '2024-05-14', '2024-11-07', 1, 'P_014'),
	('P_015-009-001', 23, 1500, 1600, '2024-07-11', '2024-09-05', 1, 'P_015');

-- Dumping structure for table pharma_care.supplier
CREATE TABLE IF NOT EXISTS `supplier` (
  `id` varchar(10) NOT NULL,
  `company_id` int NOT NULL,
  `status_id` int NOT NULL,
  `user_id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_supplier_company1_idx` (`company_id`),
  KEY `fk_supplier_status1_idx` (`status_id`),
  KEY `fk_supplier_user1_idx` (`user_id`),
  CONSTRAINT `fk_supplier_company1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `fk_supplier_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_supplier_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.supplier: ~4 rows (approximately)
INSERT INTO `supplier` (`id`, `company_id`, `status_id`, `user_id`) VALUES
	('SP_001', 1, 1, 'U_0003'),
	('SP_002', 2, 1, 'U_0004'),
	('SP_003', 5, 1, 'U_0018'),
	('SP_004', 8, 1, 'U_0019');

-- Dumping structure for table pharma_care.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(10) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `join_date` datetime NOT NULL,
  `user_type_id` int NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_user_type1_idx` (`user_type_id`),
  KEY `fk_user_status1_idx` (`status_id`),
  CONSTRAINT `fk_user_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_user_user_type1` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.user: ~19 rows (approximately)
INSERT INTO `user` (`id`, `fname`, `lname`, `email`, `join_date`, `user_type_id`, `status_id`) VALUES
	('U_0001', 'Yasoja', 'Wijerathna', 'yasoja@gmail.com', '2024-08-20 00:49:43', 1, 1),
	('U_0003', 'Akila', 'Sandeepa', 'akila@gmail.com', '2024-08-24 15:31:05', 3, 1),
	('U_0004', 'Buddhi', 'Dasith', 'dasith@gmail.com', '2024-08-24 15:31:21', 3, 1),
	('U_0005', 'Yasoja', 'Wije', 'wije@gmail.com', '2024-08-30 15:07:35', 2, 1),
	('U_0006', 'Jane', 'Smith', 'jane.smith@gmail.com', '2024-08-24 20:55:30', 2, 1),
	('U_0007', 'Robert', 'Brown', 'robert.brown@gmail.com', '2024-08-24 20:56:00', 2, 1),
	('U_0008', 'Emily', 'Davis', 'emily.davis@gmail.com', '2024-08-24 20:56:30', 2, 1),
	('U_0009', 'Michael', 'Johnson', 'michael.johnson@gmail.com', '2024-08-24 20:57:00', 2, 2),
	('U_0010', 'Patricia', 'Wilson', 'patricia.wilson@gmail.com', '2024-08-24 20:57:30', 2, 1),
	('U_0011', 'Linda', 'Martinez', 'linda.martinez@gmail.com', '2024-08-24 20:58:00', 2, 1),
	('U_0012', 'James', 'Clark', 'james.clark@gmail.com', '2024-08-24 20:58:30', 2, 2),
	('U_0013', 'Barbara', 'Lewis', 'barbara.lewis@gmail.com', '2024-08-24 20:59:00', 2, 2),
	('U_0014', 'William', 'Lee', 'william.lee@gmail.com', '2024-08-24 20:59:30', 2, 1),
	('U_0015', 'Theja', 'Thunjaya', 'theja@gmail.com', '2024-08-28 00:00:00', 2, 1),
	('U_0016', 'Malithi', 'Madawa', 'malith@gmail.com', '2024-08-28 00:00:00', 2, 1),
	('U_0017', 'W.D.Sumithra', 'Damayanthi', 'sumithra@gmail.com', '2024-08-28 00:00:00', 2, 1),
	('U_0018', 'Peter', 'Perera', 'perter@gmail.com', '2024-08-31 00:00:00', 3, 1),
	('U_0019', 'Anil', 'Ranathunga', 'anil@gmail.com', '2024-09-01 00:00:00', 3, 1),
	('U_0020', 'Tharaka', 'Tharupathi', 'tharaka34@gmail.com', '2024-09-01 00:00:00', 2, 1);

-- Dumping structure for table pharma_care.user_type
CREATE TABLE IF NOT EXISTS `user_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table pharma_care.user_type: ~3 rows (approximately)
INSERT INTO `user_type` (`id`, `type`) VALUES
	(1, 'ADMIN'),
	(2, 'CASHIER'),
	(3, 'SUPPLIER');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
