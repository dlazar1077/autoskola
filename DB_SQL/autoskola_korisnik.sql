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
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `KORISNIK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ULOGA_ID` int(11) NOT NULL,
  `IME` varchar(45) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `PREZIME` varchar(45) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `KORISNICKO_IME` varchar(45) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `EMAIL` varchar(45) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `LOZINKA` varchar(128) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `OIB` varchar(20) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `ORDINAL` int(11) DEFAULT '1',
  `DELETED` int(11) DEFAULT '0',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `MODIFICATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`KORISNIK_ID`),
  UNIQUE KEY `KORISNICKO_IME_UNIQUE` (`KORISNICKO_IME`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`),
  KEY `FK_ULOGA` (`ULOGA_ID`),
  CONSTRAINT `FK_ULOGA` FOREIGN KEY (`ULOGA_ID`) REFERENCES `uloge` (`ULOGA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (1,1,'Marino','Marić','mmaric','mmaric@gmail.com','$2a$12$lxzvaH9hVT0iipHJNGzHXOill41IC7U0Yd1UaiJ8hhK72M2Mznavi','123456789',1,0,'2022-08-31 16:27:46','2022-09-13 08:25:41'),(2,2,'Ana','Anić','aanic','aanic@gmail.com','$2a$10$iFPbEkX5LRjsco/dOssEyOhgtt3F91kxQlFWo3cYp27joQZ/HzlpG','1234567890',1,0,'2022-08-31 16:27:46','2022-09-05 14:56:29'),(3,3,'Željko','Horvat','zhorvat','davidlazar1.e@gmail.com','$2a$10$5vjbiTWsJvz0sm3L.XUWVeN6XaAyOpbPWpJ2KyLpef4EcFSLC439y','213421432',1,0,'2022-08-31 16:27:46','2022-09-15 11:32:30'),(7,2,'Dario','Daric','ddaric','ddaric@gmail.com','$2a$10$aTl1aLRmaPyKuTR4iyYyGeQwilB/l7Vd/FelmgdWJIyr.stKsHV1e','1243124324',1,0,'2022-09-01 11:51:56','2022-09-08 12:12:30'),(8,2,'dsa','dsa','dsa','dsa','$2a$10$MlQd7jH2swT0e9gxZnkn0OWHjHnThFzKMhDChMu/00u4deJCbAGgG','dsa',1,1,'2022-09-02 17:30:43','2022-09-02 17:31:22'),(9,3,'Ivona','Lazar','ilazar','ivona1227@gmail.com','$2a$10$.FfBxb.gslqq600TimiK5OHoCNkiMk1IuvzTkYKJD0VuAaphDtQ9i','321321321',1,0,'2022-09-08 15:04:50','2022-09-13 18:59:09'),(10,2,'Doris','Lazar','dorisl','doris.lazar@gmail.com','$2a$10$dUuX.A3zFD/G7j5DuZeHpeVmG8BoFEQkzlPEyH469uDg.j81F3oWu','5375398923736',1,0,'2022-09-08 15:48:55','2022-09-13 19:23:05'),(11,3,'Kruno','Krunic','kkrunic','kkrunic@gmail.com','$2a$10$g1Ut3EbTemolIHWh1.Ld9.tHjSVWJoTtvvxY9RUa.4zBGwSPiR9we','1111',1,0,'2022-09-09 12:43:43','2022-09-09 12:48:47'),(12,3,'Hermina ','Sertić','hsertic','herminkaserticova@gmail.com','$2a$10$62ZzjgyH62echngltqFB/ugekgLoMxOfqlsl2m4hvt5gSC7zOU0ma',NULL,1,0,'2022-09-09 12:57:39','2022-09-09 12:57:39'),(13,3,'Anto','Antic','aantic','aantic@gmail.com','$2a$10$Uq.HMM7AfbkauJVaj6Cen.88tfN9dEfPt5AVBua43cDqG23PZ24wq',NULL,1,0,'2022-09-12 12:12:37','2022-09-12 12:12:37'),(14,3,'Pero','Peric','pperic','pperic@gmail.com','$2a$10$hCkCrvq6fHawSKy.N9sXaOxKo.OQk0ojIPFNb2Axt3aDCIBkPOA6S','123124324',1,0,'2022-09-13 09:00:37','2022-09-13 09:01:28'),(15,3,'Mirko','Miric','mmiric','mmiric@gmail.com','$2a$10$DyTXpodUZ1IN0HwD2mMzbev32CRawYuvNeuLB9hCWKKzWKadoHlPm',NULL,1,0,'2022-09-13 18:59:57','2022-09-13 19:23:05'),(16,3,'Roko','Rokic','rrokic','rrokic@gmail.com','$2a$10$c1w.fbIFxhpqRzO0U86PVOk41dpqGTT7tcKiahGjY82cfRa6z0QTO',NULL,1,0,'2022-09-13 19:36:59','2022-09-13 19:36:59');
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `autoskola`.`korisnik_BEFORE_UPDATE` BEFORE UPDATE ON `korisnik` FOR EACH ROW
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

-- Dump completed on 2022-09-18 23:50:39
