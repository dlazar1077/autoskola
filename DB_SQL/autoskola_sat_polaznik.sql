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
-- Table structure for table `sat_polaznik`
--

DROP TABLE IF EXISTS `sat_polaznik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sat_polaznik` (
  `SAT_POLAZNIK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `POLAZNIK_ID` int(11) NOT NULL,
  `SAT_VOZNJE_ID` int(11) NOT NULL,
  `ORDINAL` int(11) DEFAULT '1',
  `DELETED` int(11) DEFAULT '0',
  PRIMARY KEY (`SAT_POLAZNIK_ID`),
  KEY `SAT_POLAZNIK_POLAZNIK_FK_idx` (`POLAZNIK_ID`),
  KEY `SAT_POLAZNIK_SAT_VOZNJE:FK_idx` (`SAT_VOZNJE_ID`),
  CONSTRAINT `SAT_POLAZNIK_POLAZNIK_FK` FOREIGN KEY (`POLAZNIK_ID`) REFERENCES `polaznik` (`POLAZNIK_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `SAT_POLAZNIK_SAT_VOZNJE:FK` FOREIGN KEY (`SAT_VOZNJE_ID`) REFERENCES `sat_voznje` (`SAT_VOZNJE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sat_polaznik`
--

LOCK TABLES `sat_polaznik` WRITE;
/*!40000 ALTER TABLE `sat_polaznik` DISABLE KEYS */;
INSERT INTO `sat_polaznik` VALUES (2,2,4,1,0),(3,2,5,1,0),(4,2,6,1,0),(5,2,7,1,0),(6,2,8,1,0),(7,2,9,1,0),(8,2,10,1,0),(9,2,11,1,0),(10,2,12,1,0),(11,2,13,1,0),(12,2,14,1,0),(13,2,15,1,0),(14,2,16,1,0),(15,2,17,1,0),(16,2,18,1,0),(17,2,19,1,0),(18,2,20,1,0),(19,2,21,1,0),(20,2,22,1,0),(21,2,23,1,0),(22,2,24,1,0),(23,2,25,1,0),(24,2,26,1,0),(25,2,27,1,0),(26,2,28,1,0),(27,2,29,1,0),(28,2,30,1,0),(29,2,31,1,0),(30,2,32,1,0),(31,2,33,1,0),(32,2,34,1,0),(33,2,35,1,0),(34,2,36,1,0),(35,2,37,1,0),(36,2,38,1,0),(37,2,39,1,0),(38,3,40,1,0),(39,3,41,1,0),(40,3,42,1,0),(41,6,43,1,0),(42,3,44,1,0),(43,6,45,1,0),(44,3,46,1,0),(45,3,47,1,0),(46,3,48,1,0),(47,3,49,1,0),(48,3,50,1,0),(49,3,51,1,0),(50,3,52,1,0),(51,3,53,1,0),(52,3,54,1,0),(53,3,55,1,0),(54,3,56,1,0),(55,3,57,1,0),(56,3,58,1,0),(57,3,59,1,0),(58,3,60,1,0),(59,3,61,1,0),(60,3,62,1,0),(61,3,63,1,0),(62,3,64,1,0),(63,3,65,1,0),(64,3,66,1,0),(65,3,67,1,0),(66,3,68,1,0),(67,3,69,1,0),(68,3,70,1,0),(69,3,71,1,0),(70,3,72,1,0),(71,3,73,1,0),(72,3,74,1,0),(73,3,75,1,0),(74,3,76,1,0);
/*!40000 ALTER TABLE `sat_polaznik` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-18 23:50:36
