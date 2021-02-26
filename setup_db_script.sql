CREATE DATABASE  IF NOT EXISTS `notes` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `notes`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: notes
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notes` (
  `note_id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `last_modified_date` datetime(6) NOT NULL,
  `last_version` int NOT NULL,
  PRIMARY KEY (`note_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `notes` WRITE;
/*!40000 ALTER TABLE `notes` DISABLE KEYS */;
INSERT INTO `notes` VALUES (1,'2021-02-26 12:58:31.114346',_binary '\0','2021-02-26 20:45:15.055414',3),(2,'2021-02-26 12:58:41.622621',_binary '\0','2021-02-26 12:58:41.622621',1),(3,'2021-02-26 12:58:46.471594',_binary '','2021-02-26 20:45:38.145652',2),(4,'2021-02-26 20:44:44.621908',_binary '\0','2021-02-26 20:44:44.621908',1);
/*!40000 ALTER TABLE `notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notes_history`
--

DROP TABLE IF EXISTS `notes_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notes_history` (
  `note_history_id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `modified_date` datetime(6) NOT NULL,
  `title` varchar(255) NOT NULL,
  `version` int NOT NULL,
  `note_id` int DEFAULT NULL,
  PRIMARY KEY (`note_history_id`),
  KEY `FKnaofa6ll75wrxllj70q6v3h51` (`note_id`),
  CONSTRAINT `FKnaofa6ll75wrxllj70q6v3h51` FOREIGN KEY (`note_id`) REFERENCES `notes` (`note_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `notes_history` WRITE;
/*!40000 ALTER TABLE `notes_history` DISABLE KEYS */;
INSERT INTO `notes_history` VALUES 
(1,'Very important note.','2021-02-26 12:58:31.182157','Some note',1,1),
(2,'This is content of note note 2.','2021-02-26 12:58:41.631596','Note 2',1,2),
(3,'Very good content, trust me.','2021-02-26 12:58:46.479574','Note 3',1,3),
(4,'Very important note.','2021-02-26 13:02:10.990883','Note 1',2,1),
(5,'This note is better then other.','2021-02-26 20:44:44.660800','Note 4',1,4),
(6,'Very important note.','2021-02-26 20:45:15.068390','Important note',3,1),
(7,'Very good content, trust me.','2021-02-26 20:45:38.151636','Note 3',2,3);
/*!40000 ALTER TABLE `notes_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;