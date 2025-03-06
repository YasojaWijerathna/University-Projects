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


-- Dumping database structure for starfield
CREATE DATABASE IF NOT EXISTS `starfield` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `starfield`;

-- Dumping structure for table starfield.batch
CREATE TABLE IF NOT EXISTS `batch` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table starfield.batch: ~5 rows (approximately)
INSERT INTO `batch` (`id`, `name`) VALUES
	(1, 'Bison'),
	(2, 'Moose'),
	(3, 'Shark'),
	(4, 'Whale'),
	(5, 'Cobra');

-- Dumping structure for table starfield.class
CREATE TABLE IF NOT EXISTS `class` (
  `id` varchar(10) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `class_day_id` int NOT NULL,
  `teacher_id` varchar(10) NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_class_class_day1_idx` (`class_day_id`),
  KEY `fk_class_teacher1_idx` (`teacher_id`),
  KEY `fk_class_status1_idx` (`status_id`),
  CONSTRAINT `fk_class_class_day1` FOREIGN KEY (`class_day_id`) REFERENCES `class_day` (`id`),
  CONSTRAINT `fk_class_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_class_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table starfield.class: ~8 rows (approximately)
INSERT INTO `class` (`id`, `start_time`, `end_time`, `class_day_id`, `teacher_id`, `status_id`) VALUES
	('C_001', '14:30:00', '18:30:00', 1, 'T_000', 3),
	('C_002', '17:00:00', '22:00:00', 3, 'T_002', 8),
	('C_003', '08:30:00', '12:30:00', 9, 'T_003', 4),
	('C_004', '09:00:00', '13:00:00', 2, 'T_003', 4),
	('C_005', '12:00:00', '17:00:00', 4, 'T_001', 3),
	('C_006', '09:30:00', '14:30:00', 5, 'T_000', 7),
	('C_007', '08:00:00', '12:00:00', 2, 'T_000', 7),
	('C_008', '15:30:00', '18:30:00', 9, 'T_001', 3);

-- Dumping structure for table starfield.class_day
CREATE TABLE IF NOT EXISTS `class_day` (
  `id` int NOT NULL AUTO_INCREMENT,
  `day` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table starfield.class_day: ~9 rows (approximately)
INSERT INTO `class_day` (`id`, `day`) VALUES
	(1, 'Monday'),
	(2, 'Tuesday'),
	(3, 'Wednesday'),
	(4, 'Thursday'),
	(5, 'Friday'),
	(6, 'Saturday'),
	(7, 'Sunday'),
	(8, 'Weekdays'),
	(9, 'Weekends');

-- Dumping structure for table starfield.class_enroll
CREATE TABLE IF NOT EXISTS `class_enroll` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class_id` varchar(10) NOT NULL,
  `student_ID` varchar(10) NOT NULL,
  `payment_status` int NOT NULL DEFAULT '6',
  PRIMARY KEY (`id`),
  KEY `fk_class_enroll_class1_idx` (`class_id`),
  KEY `fk_class_enroll_student1_idx` (`student_ID`),
  KEY `fk_class_enroll_status1_idx` (`payment_status`),
  CONSTRAINT `fk_class_enroll_class1` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `fk_class_enroll_status1` FOREIGN KEY (`payment_status`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_class_enroll_student1` FOREIGN KEY (`student_ID`) REFERENCES `student` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table starfield.class_enroll: ~16 rows (approximately)
INSERT INTO `class_enroll` (`id`, `class_id`, `student_ID`, `payment_status`) VALUES
	(1, 'C_001', 'ST_001', 6),
	(2, 'C_003', 'ST_001', 5),
	(3, 'C_002', 'ST_002', 6),
	(4, 'C_004', 'ST_001', 5),
	(5, 'C_004', 'ST_002', 6),
	(6, 'C_004', 'ST_003', 6),
	(7, 'C_001', 'ST_001', 6),
	(8, 'C_001', 'ST_002', 5),
	(9, 'C_001', 'ST_003', 5),
	(10, 'C_001', 'ST_004', 6),
	(11, 'C_005', 'ST_004', 6),
	(12, 'C_001', 'ST_001', 6),
	(13, 'C_001', 'ST_002', 6),
	(14, 'C_001', 'ST_003', 6),
	(15, 'C_001', 'ST_005', 6),
	(16, 'C_001', 'ST_006', 6);

-- Dumping structure for table starfield.invoice
CREATE TABLE IF NOT EXISTS `invoice` (
  `id` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `class_id` varchar(10) NOT NULL,
  `student_ID` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_class1_idx` (`class_id`),
  KEY `fk_invoice_student1_idx` (`student_ID`),
  CONSTRAINT `fk_invoice_class1` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `fk_invoice_student1` FOREIGN KEY (`student_ID`) REFERENCES `student` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table starfield.invoice: ~4 rows (approximately)
INSERT INTO `invoice` (`id`, `date`, `class_id`, `student_ID`) VALUES
	('1723826659861', '2024-08-16', 'C_004', 'ST_001'),
	('1723920775388', '2024-08-18', 'C_001', 'ST_003'),
	('1723921028308', '2024-08-18', 'C_001', 'ST_002'),
	('1723970675318', '2024-08-18', 'C_003', 'ST_001');

-- Dumping structure for table starfield.login
CREATE TABLE IF NOT EXISTS `login` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `status_id` int NOT NULL DEFAULT '1',
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_login_status_idx` (`status_id`),
  KEY `fk_login_user1_idx` (`user_id`),
  CONSTRAINT `fk_login_status` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_login_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table starfield.login: ~0 rows (approximately)
INSERT INTO `login` (`id`, `username`, `password`, `status_id`, `user_id`) VALUES
	(1, 'yaswije', 'Colombo@123', 1, 1);

-- Dumping structure for table starfield.status
CREATE TABLE IF NOT EXISTS `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table starfield.status: ~8 rows (approximately)
INSERT INTO `status` (`id`, `status`) VALUES
	(1, 'ACTIVE'),
	(2, 'INACTIVE'),
	(3, 'ONGOING'),
	(4, 'COMPLETED'),
	(5, 'PAID'),
	(6, 'UNPAID'),
	(7, 'REGISTERED'),
	(8, 'UNASSIGNED');

-- Dumping structure for table starfield.student
CREATE TABLE IF NOT EXISTS `student` (
  `ID` varchar(10) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `nic` varchar(12) NOT NULL,
  `mobile1` varchar(10) NOT NULL,
  `mobile2` varchar(10) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `Batch_id` int NOT NULL,
  `status_id` int NOT NULL DEFAULT '1',
  `dob` date NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_student_Batch1_idx` (`Batch_id`),
  KEY `fk_student_status1_idx` (`status_id`),
  CONSTRAINT `fk_student_Batch1` FOREIGN KEY (`Batch_id`) REFERENCES `batch` (`id`),
  CONSTRAINT `fk_student_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table starfield.student: ~6 rows (approximately)
INSERT INTO `student` (`ID`, `fname`, `lname`, `nic`, `mobile1`, `mobile2`, `email`, `Batch_id`, `status_id`, `dob`) VALUES
	('ST_001', 'Yasoja', 'Wijerathna', '200526603160', '0705702732', '', 'yasojaWije@gmail.com', 1, 1, '2005-09-22'),
	('ST_002', 'Subodha', 'Dushshantha', '200519462578', '0784561239', NULL, 'subodha@gmail.com', 1, 1, '2005-10-12'),
	('ST_003', 'Oneth', 'Isum', '200614532678', '0761234568', NULL, 'oneth@gmail.com', 1, 1, '2006-01-08'),
	('ST_004', 'Pamith', 'Ekamin', '200625468915', '0748954285', NULL, 'pamith@gmail.com', 2, 1, '2006-01-13'),
	('ST_005', 'Chamara', 'Dahanayaka', '200426123589', '0768954326', '', 'chamara@gmail.com', 1, 1, '2006-05-15'),
	('ST_006', 'Upul', 'Tharanga', '200653154652', '0781234568', '', 'upul@gmail.com', 1, 1, '2006-04-07');

-- Dumping structure for table starfield.subject
CREATE TABLE IF NOT EXISTS `subject` (
  `id` varchar(8) NOT NULL,
  `name` varchar(30) NOT NULL,
  `fee` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table starfield.subject: ~5 rows (approximately)
INSERT INTO `subject` (`id`, `name`, `fee`) VALUES
	('S_001', 'Bio Science', 55000),
	('S_002', 'Electrical Engineering', 40000),
	('S_003', 'Information Technology', 30000),
	('S_004', 'Data Science', 40000),
	('S_005', 'English', 20000);

-- Dumping structure for table starfield.teacher
CREATE TABLE IF NOT EXISTS `teacher` (
  `id` varchar(10) NOT NULL,
  `fname` varchar(30) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `nic` varchar(12) NOT NULL,
  `mobile1` varchar(10) NOT NULL,
  `mobile2` varchar(10) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `dob` date NOT NULL,
  `subject_id` varchar(8) NOT NULL,
  `status_id` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_teacher_subject1_idx` (`subject_id`),
  KEY `fk_teacher_status1_idx` (`status_id`),
  CONSTRAINT `fk_teacher_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_teacher_subject1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table starfield.teacher: ~5 rows (approximately)
INSERT INTO `teacher` (`id`, `fname`, `lname`, `nic`, `mobile1`, `mobile2`, `email`, `dob`, `subject_id`, `status_id`) VALUES
	('T_000', 'default', '-', '-', '-', NULL, '-', '0000-00-00', 'S_001', 1),
	('T_001', 'Pasindu', 'Wikasith', '198023456829', '0784561329', '', 'pasindu@gmail.com', '1980-11-19', 'S_001', 1),
	('T_002', 'Akila', 'Sandeepa', '199523456821', '0761234896', NULL, 'akila@gmail.com', '1995-08-13', 'S_005', 1),
	('T_003', 'Tharaka', 'Tharupathi', '200546132689', '0784561327', '', 'tharaka@gmail.com', '2005-06-19', 'S_002', 1),
	('T_004', 'Isuru', 'Sampath', '200086451235', '0748954235', '', 'sampath@gmail.com', '2000-02-25', 'S_001', 1);

-- Dumping structure for table starfield.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table starfield.user: ~0 rows (approximately)
INSERT INTO `user` (`id`, `fname`, `lname`) VALUES
	(1, ' Yasoja', 'Wijerathna');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
