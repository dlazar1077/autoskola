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
-- Table structure for table `instruktor_vozilo`
--

DROP TABLE IF EXISTS `instruktor_vozilo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instruktor_vozilo` (
  `INSTRUKTOR_VOZILO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `INSTRUKTOR_ID` int(11) NOT NULL,
  `VOZILO_ID` int(11) NOT NULL,
  `ORDINAL` int(11) DEFAULT '1',
  `DELETED` int(11) DEFAULT '0',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `MODIFICATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`INSTRUKTOR_VOZILO_ID`),
  KEY `INSTRUKTOR_FK_idx` (`INSTRUKTOR_ID`),
  KEY `VOZILO_FK_idx` (`VOZILO_ID`),
  CONSTRAINT `INSTRUKTOR_FK` FOREIGN KEY (`INSTRUKTOR_ID`) REFERENCES `instruktor` (`INSTRUKTOR_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `VOZILO_FK` FOREIGN KEY (`VOZILO_ID`) REFERENCES `vozilo` (`VOZILO_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instruktor_vozilo`
--

LOCK TABLES `instruktor_vozilo` WRITE;
/*!40000 ALTER TABLE `instruktor_vozilo` DISABLE KEYS */;
INSERT INTO `instruktor_vozilo` VALUES (12,1,2,1,0,'2022-09-05 14:56:29','2022-09-05 14:56:29'),(13,3,2,1,0,'2022-09-08 12:12:30','2022-09-08 12:12:30'),(14,3,3,1,0,'2022-09-08 12:12:30','2022-09-08 12:12:30'),(15,5,2,1,0,'2022-09-08 15:48:55','2022-09-08 15:48:55');
/*!40000 ALTER TABLE `instruktor_vozilo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-08 18:10:29