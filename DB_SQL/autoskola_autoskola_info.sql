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
-- Table structure for table `autoskola_info`
--

DROP TABLE IF EXISTS `autoskola_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autoskola_info` (
  `AUTOSKOLA_INFO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SIFRA` varchar(45) NOT NULL,
  `VRIJEDNOST` varchar(600) NOT NULL,
  `ORDINAL` int(11) DEFAULT '1',
  `DELETED` int(11) DEFAULT '0',
  `MODIFICATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`AUTOSKOLA_INFO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autoskola_info`
--

LOCK TABLES `autoskola_info` WRITE;
/*!40000 ALTER TABLE `autoskola_info` DISABLE KEYS */;
INSERT INTO `autoskola_info` VALUES (1,'NASLOV_AUTOSKOLE','Autoškola Lazar',1,0,'2022-08-30 13:39:14','2022-08-29 17:48:43'),(2,'BROJ_ZAPISA','10',1,0,'2022-08-29 18:41:06','2022-08-29 18:38:02'),(3,'213','213',1,1,'2022-08-29 18:41:20','2022-08-29 18:41:13'),(4,'ADRESA_AUTOSKOLE','Miroslava Krleže 1, Varaždin',1,0,'2022-09-09 20:18:07','2022-09-09 20:18:07'),(5,'EMAIL_ADRESA','autoskola.project@gmail.com',1,0,'2022-09-12 13:53:10','2022-09-09 20:18:36'),(6,'O_NAMA','Ovo je nova autoškola, ali već uspiješno radimo 5 godina. Trenutno zapošljavamo nekoliko izvrsnih, mladih instruktora koji su voljni i željni pomoći našim polaznicma da svladaju vještinu vožnje određenih vozila. Nažalost, trenutno se kod nas mogu polagati samo A i B kategorija, a nadamo se da ćemo brzo uvesti i ostale kategorije. Prolaznost na ispitima je jako visoka u odnosnu na druge autoškole, a sve to zahvaljujući našim sjanim instruktorima i predavačima.',1,0,'2022-09-09 20:33:57','2022-09-09 20:33:57'),(7,'KONTAKT_BROJ','+385 98 959 95 34',1,0,'2022-09-09 20:34:22','2022-09-09 20:34:22');
/*!40000 ALTER TABLE `autoskola_info` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `autoskola`.`autoskola_info_BEFORE_UPDATE` BEFORE UPDATE ON `autoskola_info` FOR EACH ROW
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
