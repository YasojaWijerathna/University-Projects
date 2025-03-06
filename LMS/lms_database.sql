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


-- Dumping database structure for lms
CREATE DATABASE IF NOT EXISTS `lms` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `lms`;

-- Dumping structure for table lms.academic_has_asgn
CREATE TABLE IF NOT EXISTS `academic_has_asgn` (
  `student_Sno` varchar(15) NOT NULL,
  `teacher_Tno` varchar(15) NOT NULL,
  `assignmemt_id` varchar(15) NOT NULL,
  `academic_Ano` varchar(45) NOT NULL,
  `marks` varchar(45) DEFAULT NULL,
  `marks_status_id` int NOT NULL,
  PRIMARY KEY (`student_Sno`,`teacher_Tno`,`assignmemt_id`,`academic_Ano`),
  KEY `fk_student_has_teacher_asgn_has_academic_officer_academic_o_idx` (`academic_Ano`),
  KEY `fk_student_has_teacher_asgn_has_academic_officer_student_ha_idx` (`student_Sno`,`teacher_Tno`,`assignmemt_id`),
  KEY `fk_academic_has_asgn_marks_status1_idx` (`marks_status_id`),
  CONSTRAINT `fk_academic_has_asgn_marks_status1` FOREIGN KEY (`marks_status_id`) REFERENCES `marks_status` (`id`),
  CONSTRAINT `fk_student_has_teacher_asgn_has_academic_officer_academic_off1` FOREIGN KEY (`academic_Ano`) REFERENCES `academic_officer` (`Ano`),
  CONSTRAINT `fk_student_has_teacher_asgn_has_academic_officer_student_has_1` FOREIGN KEY (`student_Sno`, `teacher_Tno`, `assignmemt_id`) REFERENCES `student_has_teacher_asgn` (`student_Sno`, `teacher_Tno`, `assignmemt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.academic_has_asgn: ~4 rows (approximately)
INSERT INTO `academic_has_asgn` (`student_Sno`, `teacher_Tno`, `assignmemt_id`, `academic_Ano`, `marks`, `marks_status_id`) VALUES
	('S1', 'T3', 'AS1', 'AC1', '80', 1),
	('S2', 'T2', 'AS3', 'AC1', '55', 2),
	('S2', 'T3', 'AS1', 'AC1', '60', 1),
	('S3', 'T2', 'AS3', 'AC1', '75', 1);

-- Dumping structure for table lms.academic_officer
CREATE TABLE IF NOT EXISTS `academic_officer` (
  `Ano` varchar(45) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `gender_id` int NOT NULL,
  `status_id` int NOT NULL,
  `email` varchar(70) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `otp` varchar(45) DEFAULT NULL,
  `grade_id` int NOT NULL,
  PRIMARY KEY (`Ano`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_academic_officer_gender1_idx` (`gender_id`),
  KEY `fk_academic_officer_status1_idx` (`status_id`),
  KEY `fk_academic_officer_grade1_idx` (`grade_id`),
  CONSTRAINT `fk_academic_officer_gender1` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`),
  CONSTRAINT `fk_academic_officer_grade1` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`),
  CONSTRAINT `fk_academic_officer_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.academic_officer: ~2 rows (approximately)
INSERT INTO `academic_officer` (`Ano`, `firstName`, `lastName`, `gender_id`, `status_id`, `email`, `password`, `otp`, `grade_id`) VALUES
	('AC1', 'Yasoja', 'Wijerathna', 1, 2, 'yasojawijerathna@gmail.com', 'colombo123', '', 12),
	('AC2', 'Bandula', 'Wijerathna', 1, 1, 'bandula@gmail.com', 'kandy007', '64da66a83497e', 13);

-- Dumping structure for table lms.ac_profile
CREATE TABLE IF NOT EXISTS `ac_profile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `line1` varchar(45) DEFAULT NULL,
  `line2` varchar(45) DEFAULT NULL,
  `city_id` int NOT NULL,
  `academic_officer_Ano` varchar(45) NOT NULL,
  `img_path` text,
  PRIMARY KEY (`id`),
  KEY `fk_address_city1_idx` (`city_id`),
  KEY `fk_ac_address_academic_officer1_idx` (`academic_officer_Ano`),
  CONSTRAINT `fk_ac_address_academic_officer1` FOREIGN KEY (`academic_officer_Ano`) REFERENCES `academic_officer` (`Ano`),
  CONSTRAINT `fk_address_city11` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.ac_profile: ~2 rows (approximately)
INSERT INTO `ac_profile` (`id`, `line1`, `line2`, `city_id`, `academic_officer_Ano`, `img_path`) VALUES
	(1, '370/A', 'Makola South', 4, 'AC1', 'LMS_files_directory/profile_img/default_avatar.png'),
	(2, '370', 'Makola South', 4, 'AC2', 'LMS_files_directory/profile_img/default_avatar.png');

-- Dumping structure for table lms.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `email` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `gender_id` int NOT NULL,
  PRIMARY KEY (`email`),
  KEY `fk_admin_gender1_idx` (`gender_id`),
  CONSTRAINT `fk_admin_gender1` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.admin: ~0 rows (approximately)
INSERT INTO `admin` (`email`, `password`, `firstName`, `lastName`, `gender_id`) VALUES
	('yasojawijerathna@gmail.com', 'colombo123', 'Yasoja', 'Wijerathna', 1);

-- Dumping structure for table lms.admin_profile
CREATE TABLE IF NOT EXISTS `admin_profile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admin_email` varchar(45) NOT NULL,
  `line1` varchar(45) DEFAULT NULL,
  `line2` varchar(45) DEFAULT NULL,
  `city_id` int NOT NULL,
  `img_path` text,
  PRIMARY KEY (`id`),
  KEY `fk_admin_profile_admin1_idx` (`admin_email`),
  KEY `fk_admin_profile_city1_idx` (`city_id`),
  CONSTRAINT `fk_admin_profile_admin1` FOREIGN KEY (`admin_email`) REFERENCES `admin` (`email`),
  CONSTRAINT `fk_admin_profile_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.admin_profile: ~0 rows (approximately)
INSERT INTO `admin_profile` (`id`, `admin_email`, `line1`, `line2`, `city_id`, `img_path`) VALUES
	(1, 'yasojawijerathna@gmail.com', '370/A', 'Makola South', 4, 'LMS_files_directory/profile_img/Yasoja_profileImg64e361462fbd5.png');

-- Dumping structure for table lms.assignmemt
CREATE TABLE IF NOT EXISTS `assignmemt` (
  `id` varchar(15) NOT NULL,
  `asgn_path` text,
  `date_released` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.assignmemt: ~4 rows (approximately)
INSERT INTO `assignmemt` (`id`, `asgn_path`, `date_released`) VALUES
	('AS1', 'LMS_files_directory/assignments/Java Institute System _ Student Admission Print.pdf', '2023-08-13'),
	('AS2', 'LMS_files_directory/assignments/T3_Java Institute System _ Student Admission Print.pdf', '2023-08-14'),
	('AS3', 'LMS_files_directory/assignments/T2_Web Programming 1 - Research_H7DT_04_EX_01 (1) (1).pdf', '2023-08-15'),
	('AS4', 'LMS_files_directory/assignments/T3_Student Admission Print.pdf', '2023-08-21');

-- Dumping structure for table lms.city
CREATE TABLE IF NOT EXISTS `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.city: ~6 rows (approximately)
INSERT INTO `city` (`id`, `cname`) VALUES
	(1, 'Colombo'),
	(2, 'Gampaha'),
	(3, 'Kandy'),
	(4, 'Makola'),
	(5, 'Ragama'),
	(6, 'Horape');

-- Dumping structure for table lms.gender
CREATE TABLE IF NOT EXISTS `gender` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.gender: ~2 rows (approximately)
INSERT INTO `gender` (`id`, `type`) VALUES
	(1, 'Male'),
	(2, 'Female');

-- Dumping structure for table lms.grade
CREATE TABLE IF NOT EXISTS `grade` (
  `id` int NOT NULL AUTO_INCREMENT,
  `grade` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.grade: ~13 rows (approximately)
INSERT INTO `grade` (`id`, `grade`) VALUES
	(1, 'Grade 01'),
	(2, 'Grade 02'),
	(3, 'Grade 03'),
	(4, 'Grade 04'),
	(5, 'Grade 05'),
	(6, 'Grade 06'),
	(7, 'Grade 07'),
	(8, 'Grade 08'),
	(9, 'Grade 09'),
	(10, 'Grade 10'),
	(11, 'Grade 11'),
	(12, 'Grade 12'),
	(13, 'Grade 13');

-- Dumping structure for table lms.grade_change
CREATE TABLE IF NOT EXISTS `grade_change` (
  `student_Sno` varchar(15) NOT NULL,
  `payment_status_id` int NOT NULL,
  `changed_date` date DEFAULT NULL,
  `payment_id` varchar(45) DEFAULT NULL,
  KEY `fk_grade_change_student1_idx` (`student_Sno`),
  KEY `fk_grade_change_payment_status1_idx` (`payment_status_id`),
  CONSTRAINT `fk_grade_change_payment_status1` FOREIGN KEY (`payment_status_id`) REFERENCES `payment_status` (`id`),
  CONSTRAINT `fk_grade_change_student1` FOREIGN KEY (`student_Sno`) REFERENCES `student` (`Sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.grade_change: ~0 rows (approximately)
INSERT INTO `grade_change` (`student_Sno`, `payment_status_id`, `changed_date`, `payment_id`) VALUES
	('S3', 2, '2023-08-20', '64e3156fc7c44');

-- Dumping structure for table lms.marks_status
CREATE TABLE IF NOT EXISTS `marks_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.marks_status: ~2 rows (approximately)
INSERT INTO `marks_status` (`id`, `status`) VALUES
	(1, 'Pending Release'),
	(2, 'Released');

-- Dumping structure for table lms.notes
CREATE TABLE IF NOT EXISTS `notes` (
  `id` varchar(15) NOT NULL,
  `note_path` text,
  `date_released` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.notes: ~3 rows (approximately)
INSERT INTO `notes` (`id`, `note_path`, `date_released`) VALUES
	('NT1', 'LMS_files_directory/notes/T3_MCS 1- 1.1.pdf', '2023-08-17'),
	('NT2', 'LMS_files_directory/notes/T3_1.2 Number Systems MCS 1.pdf', '2023-08-17'),
	('NT3', 'LMS_files_directory/notes/T3_1.2 Number Systems MCS 1.pdf', '2023-08-17');

-- Dumping structure for table lms.payment_status
CREATE TABLE IF NOT EXISTS `payment_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.payment_status: ~2 rows (approximately)
INSERT INTO `payment_status` (`id`, `status`) VALUES
	(1, 'Not Payed'),
	(2, 'Payed');

-- Dumping structure for table lms.status
CREATE TABLE IF NOT EXISTS `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.status: ~2 rows (approximately)
INSERT INTO `status` (`id`, `status`) VALUES
	(1, 'Unverified'),
	(2, 'Verified');

-- Dumping structure for table lms.student
CREATE TABLE IF NOT EXISTS `student` (
  `Sno` varchar(15) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `gender_id` int NOT NULL,
  `status_id` int NOT NULL,
  `email` varchar(70) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `otp` varchar(45) DEFAULT NULL,
  `registeredDate` date DEFAULT NULL,
  `grade_id` int NOT NULL,
  `mobile` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Sno`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`),
  KEY `fk_student_gender_idx` (`gender_id`),
  KEY `fk_student_status1_idx` (`status_id`),
  KEY `fk_student_grade1_idx` (`grade_id`),
  CONSTRAINT `fk_student_gender` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`),
  CONSTRAINT `fk_student_grade1` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`),
  CONSTRAINT `fk_student_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.student: ~5 rows (approximately)
INSERT INTO `student` (`Sno`, `firstName`, `lastName`, `DOB`, `gender_id`, `status_id`, `email`, `password`, `otp`, `registeredDate`, `grade_id`, `mobile`) VALUES
	('S1', 'Buddhi', 'Dasith', '2005-09-14', 1, 1, 'Bdasith@gmail.com', 'horape123', NULL, '2023-08-19', 12, NULL),
	('S2', 'Subodha', 'Dushshantha', '2005-10-16', 1, 2, 'subodha@gmail.com', 'ragama123', '', '2023-08-18', 12, '0718954282'),
	('S3', 'Yasoja', 'Wijerathna', '2005-09-22', 1, 2, 'yasojawijerathna@gmail.com', 'colombo123', '', '2023-08-15', 13, '0705702732'),
	('S4', 'Yasoja', 'Wijerathna', '2014-02-11', 1, 1, 'yasojawejerathna@gmail.com', 'kandy007', '64de064f4fb4a', '2023-08-15', 12, '0711576080'),
	('S5', 'Yasoja', 'Wijerathna', '2023-02-08', 1, 1, 'potterybottle@gmail.com', 'makola123', '64e365b656768', '2023-08-21', 6, '0714567883');

-- Dumping structure for table lms.student_asgn_status
CREATE TABLE IF NOT EXISTS `student_asgn_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.student_asgn_status: ~2 rows (approximately)
INSERT INTO `student_asgn_status` (`id`, `status`) VALUES
	(1, 'Pending'),
	(2, 'Completed');

-- Dumping structure for table lms.student_has_teacher_asgn
CREATE TABLE IF NOT EXISTS `student_has_teacher_asgn` (
  `student_Sno` varchar(15) NOT NULL,
  `teacher_Tno` varchar(15) NOT NULL,
  `answer` text,
  `assignmemt_id` varchar(15) NOT NULL,
  `student_asgn_status_id` int NOT NULL,
  `teacher_asgn_status_id` int NOT NULL,
  PRIMARY KEY (`student_Sno`,`teacher_Tno`,`assignmemt_id`),
  KEY `fk_student_has_teacher1_teacher1_idx` (`teacher_Tno`),
  KEY `fk_student_has_teacher1_student1_idx` (`student_Sno`),
  KEY `fk_student_has_teacher_asgn_assignmemt1_idx` (`assignmemt_id`),
  KEY `fk_student_has_teacher_asgn_student_asgn_status1_idx` (`student_asgn_status_id`),
  KEY `fk_student_has_teacher_asgn_teacher_asgn_status1_idx` (`teacher_asgn_status_id`),
  CONSTRAINT `fk_student_has_teacher1_student1` FOREIGN KEY (`student_Sno`) REFERENCES `student` (`Sno`),
  CONSTRAINT `fk_student_has_teacher1_teacher1` FOREIGN KEY (`teacher_Tno`) REFERENCES `teacher` (`Tno`),
  CONSTRAINT `fk_student_has_teacher_asgn_assignmemt1` FOREIGN KEY (`assignmemt_id`) REFERENCES `assignmemt` (`id`),
  CONSTRAINT `fk_student_has_teacher_asgn_student_asgn_status1` FOREIGN KEY (`student_asgn_status_id`) REFERENCES `student_asgn_status` (`id`),
  CONSTRAINT `fk_student_has_teacher_asgn_teacher_asgn_status1` FOREIGN KEY (`teacher_asgn_status_id`) REFERENCES `teacher_asgn_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.student_has_teacher_asgn: ~7 rows (approximately)
INSERT INTO `student_has_teacher_asgn` (`student_Sno`, `teacher_Tno`, `answer`, `assignmemt_id`, `student_asgn_status_id`, `teacher_asgn_status_id`) VALUES
	('S1', 'T3', 'LMS_files_directory/answerSheet/Student Admission Print.pdf', 'AS1', 1, 2),
	('S1', 'T3', NULL, 'AS4', 1, 1),
	('S2', 'T2', 'LMS_files_directory/answerSheet/S2_1.2 Number Systems MCS 1.pdf', 'AS3', 2, 2),
	('S2', 'T3', '', 'AS1', 1, 2),
	('S2', 'T3', 'LMS_files_directory/answerSheet/S2_Computer Codes-02- MCS1 pdf.pdf', 'AS4', 2, 1),
	('S3', 'T2', 'LMS_files_directory/answerSheet/S3_1.2 Number Systems MCS 1.pdf', 'AS3', 2, 2),
	('S4', 'T3', NULL, 'AS4', 1, 1);

-- Dumping structure for table lms.student_has_teacher_notes
CREATE TABLE IF NOT EXISTS `student_has_teacher_notes` (
  `student_Sno` varchar(15) NOT NULL,
  `teacher_Tno` varchar(15) NOT NULL,
  `notes_id` varchar(15) NOT NULL,
  PRIMARY KEY (`student_Sno`,`teacher_Tno`,`notes_id`),
  KEY `fk_student_has_teacher_teacher1_idx` (`teacher_Tno`),
  KEY `fk_student_has_teacher_student1_idx` (`student_Sno`),
  KEY `fk_student_has_teacher_notes_notes1_idx` (`notes_id`),
  CONSTRAINT `fk_student_has_teacher_notes_notes1` FOREIGN KEY (`notes_id`) REFERENCES `notes` (`id`),
  CONSTRAINT `fk_student_has_teacher_student1` FOREIGN KEY (`student_Sno`) REFERENCES `student` (`Sno`),
  CONSTRAINT `fk_student_has_teacher_teacher1` FOREIGN KEY (`teacher_Tno`) REFERENCES `teacher` (`Tno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.student_has_teacher_notes: ~8 rows (approximately)
INSERT INTO `student_has_teacher_notes` (`student_Sno`, `teacher_Tno`, `notes_id`) VALUES
	('S1', 'T3', 'NT1'),
	('S1', 'T3', 'NT3'),
	('S2', 'T3', 'NT1'),
	('S2', 'T3', 'NT3'),
	('S3', 'T3', 'NT1'),
	('S3', 'T3', 'NT3'),
	('S4', 'T3', 'NT1'),
	('S4', 'T3', 'NT3');

-- Dumping structure for table lms.st_portal
CREATE TABLE IF NOT EXISTS `st_portal` (
  `student_Sno` varchar(15) NOT NULL,
  `payment_status_id` int NOT NULL,
  `login_date` date DEFAULT NULL,
  `expire_date` date DEFAULT NULL,
  `payment_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`student_Sno`),
  KEY `fk_st_portal_payment_status1_idx` (`payment_status_id`),
  CONSTRAINT `fk_st_portal_payment_status1` FOREIGN KEY (`payment_status_id`) REFERENCES `payment_status` (`id`),
  CONSTRAINT `fk_st_portal_student1` FOREIGN KEY (`student_Sno`) REFERENCES `student` (`Sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.st_portal: ~2 rows (approximately)
INSERT INTO `st_portal` (`student_Sno`, `payment_status_id`, `login_date`, `expire_date`, `payment_id`) VALUES
	('S2', 2, '2023-08-17', '2024-09-17', '64e07f53cb2ad'),
	('S3', 2, '2023-08-16', '2024-09-16', '64e07c65e6c33');

-- Dumping structure for table lms.st_profile
CREATE TABLE IF NOT EXISTS `st_profile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `line1` varchar(45) DEFAULT NULL,
  `line2` varchar(45) DEFAULT NULL,
  `city_id` int NOT NULL,
  `student_Sno` varchar(15) NOT NULL,
  `img_path` text,
  PRIMARY KEY (`id`),
  KEY `fk_address_city1_idx` (`city_id`),
  KEY `fk_address_student1_idx` (`student_Sno`),
  CONSTRAINT `fk_address_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `fk_address_student1` FOREIGN KEY (`student_Sno`) REFERENCES `student` (`Sno`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.st_profile: ~4 rows (approximately)
INSERT INTO `st_profile` (`id`, `line1`, `line2`, `city_id`, `student_Sno`, `img_path`) VALUES
	(1, '512/A', 'Enderamulla Road', 6, 'S1', 'LMS_files_directory/profile_img/default_avatar.png'),
	(2, '456', 'Neligama Road', 5, 'S2', 'LMS_files_directory/profile_img/default_avatar.png'),
	(3, '370A', 'Makola South', 4, 'S3', 'LMS_files_directory/profile_img/default_avatar.png'),
	(4, '370A', 'Makola South, Makola', 4, 'S5', 'LMS_files_directory/profile_img/default_avatar.png');

-- Dumping structure for table lms.subject
CREATE TABLE IF NOT EXISTS `subject` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.subject: ~4 rows (approximately)
INSERT INTO `subject` (`id`, `sname`) VALUES
	(1, 'Maths'),
	(2, 'Science'),
	(3, 'English'),
	(4, 'ICT');

-- Dumping structure for table lms.teacher
CREATE TABLE IF NOT EXISTS `teacher` (
  `Tno` varchar(15) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `grade_id` int NOT NULL,
  `subject_id` int NOT NULL,
  `gender_id` int NOT NULL,
  `status_id` int NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `otp` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Tno`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_teacher_grade1_idx` (`grade_id`),
  KEY `fk_teacher_subject1_idx` (`subject_id`),
  KEY `fk_teacher_gender1_idx` (`gender_id`),
  KEY `fk_teacher_status1_idx` (`status_id`),
  CONSTRAINT `fk_teacher_gender1` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`),
  CONSTRAINT `fk_teacher_grade1` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`),
  CONSTRAINT `fk_teacher_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_teacher_subject1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.teacher: ~4 rows (approximately)
INSERT INTO `teacher` (`Tno`, `firstName`, `lastName`, `grade_id`, `subject_id`, `gender_id`, `status_id`, `email`, `password`, `otp`) VALUES
	('T1', 'Subodha', 'Dushshantha', 12, 1, 1, 1, 'subodha@gmail.com', 'colombo123', '64d7c8f3eaeb8'),
	('T2', 'Darshani', 'Nanayakkara', 12, 3, 2, 2, 'dnanayakkara001@gmail.com', 'colombo123', ''),
	('T3', 'Yasoja', 'Wijerathna', 12, 4, 1, 2, 'yasojawijerathna@gmail.com', 'colombo123', '64d7cf11c0421'),
	('T4', 'Bandula', 'Wijerathna', 13, 1, 1, 1, 'bmw_kbm2000@yahoo.com', 'matale12', '64e359f7202ce');

-- Dumping structure for table lms.teacher_asgn_status
CREATE TABLE IF NOT EXISTS `teacher_asgn_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.teacher_asgn_status: ~2 rows (approximately)
INSERT INTO `teacher_asgn_status` (`id`, `status`) VALUES
	(1, 'Marks Unassigned'),
	(2, 'Marks Assigned');

-- Dumping structure for table lms.t_profile
CREATE TABLE IF NOT EXISTS `t_profile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `line1` varchar(45) DEFAULT NULL,
  `line2` varchar(45) DEFAULT NULL,
  `city_id` int NOT NULL,
  `teacher_Tno` varchar(15) NOT NULL,
  `img_path` text,
  PRIMARY KEY (`id`),
  KEY `fk_address_city1_idx` (`city_id`),
  KEY `fk_t_address_teacher1_idx` (`teacher_Tno`),
  CONSTRAINT `fk_address_city10` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `fk_t_address_teacher1` FOREIGN KEY (`teacher_Tno`) REFERENCES `teacher` (`Tno`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table lms.t_profile: ~3 rows (approximately)
INSERT INTO `t_profile` (`id`, `line1`, `line2`, `city_id`, `teacher_Tno`, `img_path`) VALUES
	(1, '456', 'Neligama Road', 5, 'T1', 'LMS_files_directory/profile_img/default_avatar.png'),
	(2, '370', 'Makola South', 4, 'T2', 'LMS_files_directory/profile_img/Darshani_profileImg.png'),
	(3, '370/A', 'Makola South', 4, 'T3', 'LMS_files_directory/profile_img/default_avatar.png');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
