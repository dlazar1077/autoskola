-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: autoskola
-- ------------------------------------------------------
-- Server version	5.7.22

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
-- Table structure for table `ispit`
--

DROP TABLE IF EXISTS `ispit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ispit` (
  `ISPIT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `KORISNIK_ID` int(11) DEFAULT NULL,
  `MAKSIMALNI_BROJ_BODOVA` int(11) DEFAULT NULL,
  `OSTVARENI_BROJ_BODOVA` int(11) DEFAULT NULL,
  `STATUS_ISPITA` varchar(45) DEFAULT NULL,
  `ORDINAL` int(11) NOT NULL DEFAULT '1',
  `DELETED` int(11) NOT NULL DEFAULT '0',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `MODIFICATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ISPIT_ID`),
  KEY `ISPIT_KORISNIK_FK_idx` (`KORISNIK_ID`),
  CONSTRAINT `ISPIT_KORISNIK_FK` FOREIGN KEY (`KORISNIK_ID`) REFERENCES `korisnik` (`KORISNIK_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ispit`
--

LOCK TABLES `ispit` WRITE;
/*!40000 ALTER TABLE `ispit` DISABLE KEYS */;
INSERT INTO `ispit` VALUES (1,3,120,3,'PAD - Raskrižje netočno odgovoreno!',1,0,'2022-09-07 15:12:40','2022-09-07 18:54:35'),(2,3,120,7,'PAD - Raskrižje netočno odgovoreno!',1,0,'2022-09-07 15:35:43','2022-09-07 18:54:35'),(3,3,120,3,'PAD - Raskrižje netočno odgovoreno!',1,0,'2022-09-07 16:01:15','2022-09-07 18:54:35'),(4,1,120,117,'PROLAZ',1,0,'2022-09-08 09:48:03','2022-09-08 09:48:03'),(5,3,120,117,'PROLAZ',1,0,'2022-09-08 09:52:10','2022-09-08 09:52:10'),(6,9,120,92,'PAD - Premali broj bodova',1,0,'2022-09-08 15:26:35','2022-09-08 15:27:36'),(7,9,120,105,'PAD - Premali broj bodova',1,0,'2022-09-08 15:34:16','2022-09-08 15:34:16'),(8,9,120,115,'PROLAZ',1,0,'2022-09-08 15:39:17','2022-09-08 15:39:17'),(9,11,120,118,'PROLAZ',1,0,'2022-09-09 12:48:16','2022-09-09 12:48:16'),(10,12,120,75,'PAD - Raskrižje netočno odgovoreno!',1,0,'2022-09-09 13:13:56','2022-09-09 13:13:56'),(11,12,120,116,'PROLAZ',1,0,'2022-09-12 12:11:50','2022-09-12 12:11:50'),(12,14,120,0,'PAD - Raskrižje netočno odgovoreno!',1,0,'2022-09-13 09:02:57','2022-09-13 09:02:57'),(13,15,120,120,'PROLAZ',1,0,'2022-09-13 19:22:58','2022-09-13 19:22:58'),(14,14,120,6,'PAD - Raskrižje netočno odgovoreno!',1,0,'2022-09-13 21:09:28','2022-09-13 21:09:28'),(15,14,120,0,'PAD - Raskrižje netočno odgovoreno!',1,0,'2022-09-14 13:48:36','2022-09-14 13:48:36'),(16,14,120,0,'PAD - Raskrižje netočno odgovoreno!',1,0,'2022-09-15 16:23:12','2022-09-15 16:23:12'),(17,14,120,0,'PAD - Raskrižje netočno odgovoreno!',1,0,'2022-09-15 21:48:35','2022-09-15 21:48:35');
/*!40000 ALTER TABLE `ispit` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `autoskola`.`ispit_BEFORE_UPDATE` BEFORE UPDATE ON `ispit` FOR EACH ROW
BEGIN
	SET NEW.MODIFICATION_DATE = now(); 
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-18 23:50:38
